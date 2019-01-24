package com.labs.vsu.labProj.service;

import com.labs.vsu.labProj.entity.ShoppingList;
import com.labs.vsu.labProj.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository shoppingListRepository;

    public void save(ShoppingList shoppingList){
        shoppingListRepository.save(shoppingList);
    }

    public List<ShoppingList> findAll(){
        return shoppingListRepository.findAll();
    }

    public ShoppingList findByListId(long id){
        return shoppingListRepository.findByListId(id);
    }

    public void deleteByListId(long id){
        shoppingListRepository.deleteByListId(id);
    }


}
