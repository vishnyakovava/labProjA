package com.labs.vsu.labProj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ListProduct")
@Table(name = "list_product")
@AssociationOverrides({
        @AssociationOverride(name = "compositePK.shoppingList", joinColumns = @JoinColumn(name = "list_id")),
        @AssociationOverride(name = "compositePK.productDescription", joinColumns = @JoinColumn(name = "product_id"))
        })
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "compositePK")
public class ListProduct  implements Serializable {

    private static final long serialVersionUID = 1L;
    private ListProductID compositePK = new ListProductID();
    private int itemQuantity;

    public ListProduct(){} //public
    public ListProduct(int itemQuantity, ShoppingList shoppingList){
        //добавить список по айди списка,
        //добавить продукт по айди продукта
        setItemQuantity(itemQuantity);
        setShoppingList(shoppingList);
//        setProductDescription(productDescription);
    }

    @EmbeddedId
    public ListProductID getCompositePK() {
        return compositePK;
    }
    public void setCompositePK(ListProductID compositePK) {
        this.compositePK = compositePK;
    }

    @Column(name = "item_quantity")
    public int getItemQuantity() {
        return itemQuantity;
    }
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }


    @Transient
    public ShoppingList getShoppingList(){
        return getCompositePK().getShoppingList();
    }
    public void setShoppingList(ShoppingList shoppingList){
        getCompositePK().setShoppingList(shoppingList);
    }

//    @Transient
//    public ProductDescription getProductDescription(){
//        return getCompositePK().getProductDescription();
//    }
//    public void setProductDescription(ProductDescription productDescription){
//        getCompositePK().setProductDescription(productDescription);
//    }

    @Override
    public String toString(){
        return "Shopping list:\n" + getShoppingList().toString()
//                + "\nproduct description: \n" + getProductDescription().toString()
                + "\nitem quantity: " + getItemQuantity();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ListProduct that = (ListProduct) o;

        if (getCompositePK() != null ? !getCompositePK().equals(that.getCompositePK())
                : that.getCompositePK() != null)
            return false;

        return true;
    }
    @Override
    public int hashCode() {
        return (getCompositePK() != null ? getCompositePK().hashCode() : 0);
    }
}


