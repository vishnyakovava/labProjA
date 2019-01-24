package com.labs.vsu.labProj.controller;

import com.labs.vsu.labProj.entity.ListProduct;
import com.labs.vsu.labProj.service.ListProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rest/listproducts")
public class ListProductController {

    @Autowired
    ListProductService listProductService;
    Logger logger = LoggerFactory.getLogger(ListProductController.class);

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<ListProduct> findAll(){
        List<ListProduct> listProducts = listProductService.findAll();
        return listProducts;
    }

    @RequestMapping(value = "/findone", method = RequestMethod.GET)
    public ListProduct findByListIdAndProductId(@RequestParam("listId") long listId, @RequestParam("productId") long productId){
        ListProduct listProduct = listProductService.findByListIdAndProductId(listId, productId);
        return listProduct;
    }

    @RequestMapping(value = "/delete/{listId}/{productId}", method = RequestMethod.DELETE,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deleteByListIdAndProductId(@RequestParam("listId") long listId, @RequestParam("productId") long productId){
        listProductService.deleteByListIdAndProductId(listId, productId);
        return "List of products was deleted";
    }

    @RequestMapping(value = "/save", method=RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String saveListProducts(@RequestBody String listValues){
        // parse string to objects ---- take substr, take product and create new Product Description obj,
        // save product, create shopping list and save
        // create list product and save for product description and shopping list!!!!!!!!!!


        logger.info("values" + listValues);
        return "got";
    }




}
