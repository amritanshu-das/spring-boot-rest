package com.win.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.win.model.Product;
import com.win.model.Sku;
import com.win.vo.ProductBody;
import com.win.vo.SkuBody;
import com.win.service.CatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogService catalogService;
    
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable(value = "productId") Long pProductId) {
        return getCatalogService().getProduct(pProductId);
    }

    @GetMapping("/skus/{skuId}")
    public Sku getSku(@PathVariable(value = "skuId") Long pSkuId) {
        return getCatalogService().getSku(pSkuId);
    }

    @GetMapping("/allSkus")
    public Set<SkuBody> getAllSkus() {
        return getCatalogService().getAllSkus();
    }

    @GetMapping("/allProducts")
    public Iterable<Sku> getAllProducts() {
        return getCatalogService().getAllProducts();
    }

    @PostMapping("/createProduct")
    public Map<String, Object> createProduct(@RequestBody ProductBody pProduct) {
        final Long lProductId = getCatalogService().createProduct(pProduct);
        final Map<String, Object> lResponseMap = new HashMap<String, Object>(2);
        lResponseMap.put("success", true);
        lResponseMap.put("productId", lProductId);
        return lResponseMap;
    }

    @PostMapping("/createSku")
    public Map<String, Object> createSku(@RequestBody SkuBody pSku) {
        final Long lSkuId = getCatalogService().createSku(pSku);
        final Map<String, Object> lResponseMap = new HashMap<String, Object>(2);
        lResponseMap.put("success", true);
        lResponseMap.put("skuId", lSkuId);
        return lResponseMap;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    @Autowired
    public void setCatalogService(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
}