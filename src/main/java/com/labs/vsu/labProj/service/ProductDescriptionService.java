package com.labs.vsu.labProj.service;
import com.labs.vsu.labProj.entity.ProductDescription;
import com.labs.vsu.labProj.repository.ProductDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDescriptionService {

    @Autowired
    ProductDescriptionRepository productDescriptionRepository;

    public void save(ProductDescription productDescription){
        productDescriptionRepository.save(productDescription);
    }

    public ProductDescription findByProductId(long id){
       return productDescriptionRepository.findByProductId(id);
    }

    public List<ProductDescription> findByProductName(String name){
        return productDescriptionRepository.findByProductName(name);
    }

    public List<ProductDescription> findAll(){
        return productDescriptionRepository.findAll();
    }

    public void deleteByProductId(long id){
        productDescriptionRepository.deleteByProductId(id);
    }
}
