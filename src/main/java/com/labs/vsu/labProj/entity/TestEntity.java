package com.labs.vsu.labProj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

public class TestEntity {

    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("product")
    private ProductDescription productDescription;

    public TestEntity(int quantity, ProductDescription productDescription){
        setProductDescription(productDescription);
        setQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }
}
