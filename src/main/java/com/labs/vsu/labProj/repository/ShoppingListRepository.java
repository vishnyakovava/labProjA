package com.labs.vsu.labProj.repository;

import com.labs.vsu.labProj.entity.ShoppingList;
import com.labs.vsu.labProj.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

//    ShoppingList findByUserId(@Param("user_id") long userId);
    ShoppingList findByListId(@Param("list_id") long listId);

    void deleteByListId(@Param("list_id") long listId);

    @Override
    List<ShoppingList> findAll();
}
