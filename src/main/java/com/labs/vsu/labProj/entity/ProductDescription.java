package com.labs.vsu.labProj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import sun.plugin.perf.PluginRollup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_description")
public class ProductDescription implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    private long productId;
    private String productName;
    private Purchase purchase;

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("productId")
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Column(name = "product_name")
    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

//    @ManyToOne // deleted cascade
//    @JoinColumn(name = "purchase_id")
//    @JsonBackReference
        //    @OneToOne(mappedBy="productDescription", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        //    @JoinColumn(name="pl_id")
    @OneToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    //    @JsonManagedReference
    public Purchase getPurchase() {
        return purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public ProductDescription(String productName, Purchase purchases){
        setProductName(productName);
        setPurchase(purchases);
    }
    public ProductDescription(){}


    @Override
    public String toString(){
        return String.format("Product [%d]: product name - [%s] , purchase - [%s] ",
                productId,
                productName,
                getPurchase().toString());
    }
}
