package com.win.service;

import java.util.HashSet;
import java.util.Set;

import com.win.model.Product;
import com.win.model.Sku;
import com.win.vo.ProductBody;
import com.win.vo.SkuBody;
import com.win.repository.ProductRepository;
import com.win.repository.SkuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    private ProductRepository productRepository;

    private SkuRepository skuRepository;

    public Sku getSku(final Long pSkuId) {
        return getSkuRepository().findById(pSkuId).get();
    }

    public Product getProduct(final Long pProductId) {
        return getProductRepository().findById(pProductId).get();
    }

    public Set<SkuBody> getAllSkus() {
        Iterable<Sku> lSkus = getSkuRepository().findAll();
        Set<SkuBody> lSkuSet = new HashSet<SkuBody>();
        lSkus.forEach(lSku -> {
            SkuBody lSkuBody = new SkuBody();
            lSkuBody.setColor(lSku.getColor());
            lSkuBody.setSize(lSku.getSize());
            lSkuBody.setFeatures(lSku.getFeatures());
            ProductBody lProductBody = new ProductBody();
            Product lParentProduct = lSku.getParentProduct();
            
            lProductBody.setBrand(lParentProduct.getBrand());
            lProductBody.setName(lParentProduct.getName());
            lProductBody.setLongDescription(lParentProduct.getLongDescription());
            
            lSkuBody.setParentProduct(lProductBody);

            lSkuSet.add(lSkuBody);
        });
        return lSkuSet;
    }

    public Iterable<Sku> getAllProducts() {
        return getSkuRepository().findAll();
    }

    public Long createSku(final SkuBody pSku) {
        Sku lSku = null;

        if (pSku.getSkuId() != null) {
            lSku = getSkuRepository().findById(pSku.getSkuId()).get();
        } else {
            lSku = new Sku();
        }
        lSku.setColor(pSku.getColor());
        lSku.setSize(pSku.getSize());
        lSku.setFeatures(pSku.getFeatures());
        if (pSku.getParentProductId() != null) {
            final Product lParentProduct = getProductRepository().findById(pSku.getParentProductId()).get();
            if (lParentProduct == null) {
                throw new RuntimeException("Parent Product not found");
            }
            lSku.setParentProduct(lParentProduct);
        }
        lSku = getSkuRepository().save(lSku);
        return lSku.getSkuId();
    }

    public Long createProduct(final ProductBody pProduct) {
        Product lProduct = null;
        if (pProduct.getProductId() != null) {
            lProduct = getProductRepository().findById(pProduct.getProductId()).get();
        } else {
            lProduct = new Product();
        }
        lProduct.setBrand(pProduct.getBrand());
        lProduct.setLongDescription(pProduct.getLongDescription());
        lProduct.setName(pProduct.getName());
        lProduct = getProductRepository().save(lProduct);

        return lProduct.getProductId();
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Autowired
    public void setProductRepository(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public SkuRepository getSkuRepository() {
        return skuRepository;
    }

    @Autowired
    public void setSkuRepository(final SkuRepository skuRepository) {
        this.skuRepository = skuRepository;
    }

}