package com.labs.vsu.labProj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="purchase")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    private long purchaseId;
    private String purchaseAddress;
    private String purchaseName;
    private ProductDescription productDescription;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Column(name = "purchase_address", nullable = false, unique = true)
    public String getPurchaseAddress() {
        return purchaseAddress;
    }
    public void setPurchaseAddress(String purchaseAddress) {
        this.purchaseAddress = purchaseAddress;
    }

    @Column(name = "purchase_name", nullable = false)
    public String getPurchaseName() {
        return purchaseName;
    }
    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

//    @OneToMany(mappedBy="purchase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonManagedReference
    @OneToOne// deleted cascade
//    @JoinColumn(name = "product_id")
//    @Fetch(FetchMode.JOIN)
    public ProductDescription getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public Purchase(){}
    public Purchase(String purchaseName, String purchaseAddress){
        setPurchaseName(purchaseName);
        setPurchaseAddress(purchaseAddress);
    }

    @Override
    public String toString(){
        return String.format("Purchase[%d]: name - [%s], address - [%s]",
                            purchaseId,
                            purchaseName,
                            purchaseAddress);
    }



}
