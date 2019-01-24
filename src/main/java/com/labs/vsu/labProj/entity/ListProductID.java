package com.labs.vsu.labProj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ListProductID implements Serializable{
    public ListProductID(){}

    private ShoppingList shoppingList;
    private ProductDescription productDescription;

    // @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    //@JsonBackReference
 //   @XmlTransient
    public ShoppingList getShoppingList() {
        return shoppingList;
    }
    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

 //   @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
 //   @JsonBackReference
 //   @XmlTransient
    public ProductDescription getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(ProductDescription productDescr) {
        this.productDescription = productDescr;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListProductID that = (ListProductID) o;

        if (shoppingList != null ? !shoppingList.equals(that.shoppingList) : that.shoppingList != null) return false;
        if (productDescription != null ? !productDescription.equals(that.productDescription) : that.productDescription != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (shoppingList != null ? shoppingList.hashCode() : 0);
        result = 31 * result + (productDescription != null ? productDescription.hashCode() : 0);
        return result;
    }





}
