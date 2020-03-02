package com.win.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProductBody {

    private Long productId;

    private String name;

    private String brand;

    private String longDescription;
    
    private List<Long> skus;

    private List<SkuBody> childSkus;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public List<Long> getSkus() {
        return skus;
    }

    public void setSkus(List<Long> skus) {
        this.skus = skus;
    }

    public List<SkuBody> getChildSkus() {
        return childSkus;
    }

    public void setChildSkus(List<SkuBody> childSkus) {
        this.childSkus = childSkus;
    }

}