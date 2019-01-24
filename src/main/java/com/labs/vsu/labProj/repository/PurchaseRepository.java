package com.labs.vsu.labProj.repository;

import com.labs.vsu.labProj.entity.Purchase;
import com.labs.vsu.labProj.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Purchase findByPurchaseId(@Param("purchase_id") long purchaseId);
    Purchase findByPurchaseAddress(@Param("purchase_address") String purchaseAddress);
    Purchase findByPurchaseName(@Param("purchase_name") String purchaseName);

    @Transactional //?
    void deleteByPurchaseAddress(@Param("purchase_address") String purchaseAddress);
    void deleteByPurchaseId(@Param("purchase_id") long purchaseId);

    @Override
    List<Purchase> findAll();
}
