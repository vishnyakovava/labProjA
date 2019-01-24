package com.labs.vsu.labProj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.vsu.labProj.entity.Purchase;
import com.labs.vsu.labProj.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/rest/purchases")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;
    Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<Purchase> findAll(){
        List<Purchase> purchases = purchaseService.findAll();
        return purchases;
    }

    @RequestMapping(value="/findbyid", method = RequestMethod.GET)
    public String findById(@RequestParam("purchaseId") long purchaseId){
        return purchaseService.findByPurchaseId(purchaseId).toString();
    }

    @RequestMapping(value="/findbyaddress", method = RequestMethod.GET)
    public String findByLastName(@RequestParam("purchaseAddress") String purchaseAddress){
        return purchaseService.findByPurchaseAddress(purchaseAddress).toString();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String savePurchase(@RequestBody String purchase){ //save object, not string?
        Purchase requestPurchase;
        ObjectMapper mapper = new ObjectMapper();
        logger.info(purchase);
        try {
            requestPurchase = mapper.readValue(purchase, Purchase.class);
            logger.info(requestPurchase.toString());
//            Purchase newPurchase = new Purchase(requestPurchase.getPurchaseName(), requestPurchase.getPurchaseAddress());
            purchaseService.save(requestPurchase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Purchase was successfully saved";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String updateUser(@RequestBody String purchase){
        ObjectMapper mapper = new ObjectMapper();
        Purchase requestPurchase;
        try {
            requestPurchase = mapper.readValue(purchase, Purchase.class);
            Purchase updatedPurchase = purchaseService.findByPurchaseAddress(requestPurchase.getPurchaseAddress());

            updatedPurchase.setPurchaseAddress(requestPurchase.getPurchaseAddress()); // no need in update unique field?
            purchaseService.save(updatedPurchase);
            } catch (IOException e) {
            e.printStackTrace();
        }
        return "Purchase was successfully updated";
    }

    @RequestMapping(value = "/delete/{purchaseAddress}", method = RequestMethod.DELETE,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deleteUser(@PathVariable("purchaseAddress") String purchaseAddress){
        purchaseService.deleteByPurchaseAddress(purchaseAddress);
        return "User was deleted";
    }

    @RequestMapping(value = "/delete/{purchaseId}", method = RequestMethod.DELETE,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deleteUser(@PathVariable("purchaseId") long purchaseId){
        purchaseService.deleteByPurchaseId(purchaseId);
        return "User was deleted";
    }

}
