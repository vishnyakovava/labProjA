package com.labs.vsu.labProj.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private long userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;

    private List<ShoppingList> shoppingList;

    @Id
    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name="user_first_name")
    public String getUserFirstName() {
        return userFirstName;
    }
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Column(name = "user_last_name")
    public String getUserLastName() {
        return userLastName;
    }
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Column(name = "user_email", unique = true, nullable = false)
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<ShoppingList> getShoppingList(){
        return shoppingList;
    }
    public void setShoppingList(List<ShoppingList> shoppingList){
        this.shoppingList = shoppingList;
    }

    public User(){}
    public User(long userId,
                String userFirstName,
                String userLastName,
                String userEmail,
                String userPassword){
        setUserId(userId);
        setUserFirstName(userFirstName);
        setUserLastName(userLastName);
        setUserEmail(userEmail);
        setUserPassword(userPassword);
    }

    @Override
    public String toString(){
        return String.format("User[%d]: first name - [%s], last name - [%s], userEmail - [%s], userPassword - [%s]",
                            userId,
                            userFirstName,
                            userLastName,
                            userEmail,
                            userPassword);
    }
}
