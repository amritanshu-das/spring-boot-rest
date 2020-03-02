package com.win.vo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SkuBody {

    private Long skuId;

    private String color;

    private String size;

    private Set<String> features;

    private Long parentProductId;

    private ProductBody parentProduct;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Set<String> getFeatures() {
        return features;
    }

    public void setFeatures(Set<String> features) {
        this.features = features;
    }

    public Long getParentProductId() {
        return parentProductId;
    }

    public void setParentProductId(Long parentProductId) {
        this.parentProductId = parentProductId;
    }

    public ProductBody getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(ProductBody parentProduct) {
        this.parentProduct = parentProduct;
    }

}