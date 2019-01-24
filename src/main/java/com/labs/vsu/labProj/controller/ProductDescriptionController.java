package com.labs.vsu.labProj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.vsu.labProj.entity.ListProduct;
import com.labs.vsu.labProj.entity.ProductDescription;
import com.labs.vsu.labProj.entity.TestEntity;
import com.labs.vsu.labProj.entity.User;
import com.labs.vsu.labProj.service.ProductDescriptionService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/rest/products")
public class ProductDescriptionController {

    @Autowired
    ProductDescriptionService productDescriptionService;
    Logger logger = LoggerFactory.getLogger(ProductDescriptionController.class);

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<ProductDescription> findAll(){
        List<ProductDescription> products = productDescriptionService.findAll();
        return products;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String saveProduct(@RequestBody String productDescription) throws IOException {
        ProductDescription requestProductDescription;
        ObjectMapper mapper = new ObjectMapper();
        logger.info(productDescription);
        try {
            requestProductDescription = mapper.readValue(productDescription, ProductDescription.class);
            logger.info(requestProductDescription.toString());
            productDescriptionService.save(requestProductDescription);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String updateProduct(@RequestBody String product){
        ProductDescription requestProductDescription;
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestProductDescription = mapper.readValue(product, ProductDescription.class);
            logger.info(requestProductDescription.toString());

            ProductDescription oldProduct = productDescriptionService.findByProductId(requestProductDescription.getProductId());
            logger.info("found: " + oldProduct.toString());

            oldProduct.setProductName(requestProductDescription.getProductName());
            oldProduct.setPurchase(requestProductDescription.getPurchase());
            productDescriptionService.save(oldProduct);
            logger.info("updated: " + oldProduct.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"statusText\": \"OK\"}";
    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE,  produces = { MediaType.APPLICATION_JSON_VALUE, //
            MediaType.APPLICATION_XML_VALUE})
    public String deleteProduct(@PathVariable("productId") long productId){
        logger.info(""+productId);
        productDescriptionService.deleteByProductId(productId);
        return "User was deleted";
    }
}
