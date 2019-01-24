package com.labs.vsu.labProj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_list")
public class ShoppingList implements Serializable {

    private static final long serialVersionUID = 1L;
    private long listId;
    private User user;
    private String address;
    private List<ListProduct> listProducts;

    @Id
    @Column(name = "list_id")
    public long getListId(){
        return listId;
    }
    public void setListId(long listId) {
        this.listId = listId;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compositePK.shoppingList")
//  //  @JsonManagedReference
//    public List<ListProduct> getListProducts() {
//        return listProducts;
//    }
//    public void setListProducts(List<ListProduct> listProducts) {
//        this.listProducts = listProducts;
//    }

    public ShoppingList(){}
    public ShoppingList(long listId, User user, String address){
        setUser(user);
        setListId(listId);
        setAddress(address);
    }
//    public ShoppingList(User user, List<ListProduct> listProducts){
//        setUser(user);
//        setListProducts(listProducts);
//    }
//    public ShoppingList(User user, List<ProductDescription> products){
//        setUser(user);
//        setProductDescription(products);
//    }

    public void addListProduct(ListProduct listProduct){
        this.listProducts.add(listProduct);
    }

    @Override
    public String toString()
    {
        return String.format(
                "List[id=%d, userId=%d]",
                listId, user.getUserId());
    }


}
