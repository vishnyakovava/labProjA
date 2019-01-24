package com.labs.vsu.labProj.service;

import com.labs.vsu.labProj.entity.ListProduct;
import com.labs.vsu.labProj.repository.ListProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.util.List;

@Service
public class ListProductService {

    @Autowired
    ListProductRepository listProductRepository;

    public void save(ListProduct listProduct){
        listProductRepository.save(listProduct);
    }

    public ListProduct findByListIdAndProductId(long listId, long productId){
        return listProductRepository.findByListIdAndProductId (listId, productId);
    }

    public List<ListProduct> findAll(){
        return listProductRepository.findAll();
    }

    public void deleteByListIdAndProductId(long listId, long productId){
        listProductRepository.deleteByListIdAndProductId(listId, productId);
    }
}
