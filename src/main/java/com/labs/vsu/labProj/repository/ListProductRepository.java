package com.labs.vsu.labProj.repository;

import com.labs.vsu.labProj.entity.ListProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ListProductRepository extends CrudRepository<ListProduct, Long> {

    @Query(value="SELECT * FROM list_product l WHERE l.list_id = ? AND l.product_id = ?", nativeQuery = true)
    ListProduct findByListIdAndProductId(@Param("list_id") long listId, @Param("product_id") long productId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM list_product l WHERE l.list_id = ? AND l.product_id = ?", nativeQuery = true)
    void deleteByListIdAndProductId(@Param("list_id") long listId, @Param("product_id") long productId);

    List<ListProduct> findAll();


}
