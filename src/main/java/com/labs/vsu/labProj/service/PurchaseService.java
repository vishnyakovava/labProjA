package com.labs.vsu.labProj.service;

import com.labs.vsu.labProj.entity.Purchase;
import com.labs.vsu.labProj.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public void save(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    public Purchase findByPurchaseId(long purchaseId){
        return purchaseRepository.findByPurchaseId(purchaseId);
    }

    public Purchase findByPurchaseAddress(String purchaseAddress){
        return purchaseRepository.findByPurchaseAddress(purchaseAddress);
    }

    public Purchase findByPurchaseName(String purchaseName){
        return purchaseRepository.findByPurchaseName(purchaseName);
    }

    public void deleteByPurchaseAddress(String purchaseAddress){
        purchaseRepository.deleteByPurchaseAddress(purchaseAddress);
    }

    public void deleteByPurchaseId(long purchaseId){
        purchaseRepository.deleteByPurchaseId(purchaseId);
    }
}
