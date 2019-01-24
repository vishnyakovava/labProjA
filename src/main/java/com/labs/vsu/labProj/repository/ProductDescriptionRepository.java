package com.labs.vsu.labProj.repository;

import com.labs.vsu.labProj.entity.ProductDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ProductDescriptionRepository extends CrudRepository<ProductDescription, Long> {
    ProductDescription findByProductId(@Param("product_id") long productId);
    List<ProductDescription> findByProductName(@Param("product_name") String productName);
    List<ProductDescription> findAll();

    void deleteByProductId(@Param("product_id") long productId);
}
