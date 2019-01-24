package com.labs.vsu.labProj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.vsu.labProj.entity.ShoppingList;
import com.labs.vsu.labProj.entity.User;
import com.labs.vsu.labProj.service.ListProductService;
import com.labs.vsu.labProj.service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/rest/shoplist")
public class ShoppingListController {

    @Autowired
    ShoppingListService shoppingListService;
    Logger logger = LoggerFactory.getLogger(ListProductController.class);

    @RequestMapping(value = "/save", method = RequestMethod.POST,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String creaetList(@RequestBody String shopList){
        logger.info(shopList);
        ShoppingList requestList;
        ObjectMapper mapper = new ObjectMapper();
        try {
            requestList = mapper.readValue(shopList, ShoppingList.class);
            logger.info(requestList.toString());
            shoppingListService.save(requestList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "List was successfully created";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String updateList(@RequestBody String list){
        ObjectMapper mapper = new ObjectMapper();
        ShoppingList requestShoplist;
        try {
            requestShoplist = mapper.readValue(list, ShoppingList.class);
            logger.info("request:"+requestShoplist.toString());

            ShoppingList oldList = shoppingListService.findByListId(requestShoplist.getListId());
            logger.info("found: " + oldList.toString());

            oldList.setAddress(requestShoplist.getAddress());
            oldList.setUser(requestShoplist.getUser());
            shoppingListService.save(oldList);
            logger.info("updated: " + oldList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "List was successfully updated";
    }

    @RequestMapping(value = "/delete/{listId}", method = RequestMethod.DELETE,  produces = { MediaType.APPLICATION_JSON_VALUE, //
            MediaType.APPLICATION_XML_VALUE})
    public String deleteList(@PathVariable("listId") long listId){
        logger.info(""+listId);
        shoppingListService.deleteByListId(listId);
        return "User was deleted";
    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<ShoppingList> findAll(){
        List<ShoppingList> shoppingList = shoppingListService.findAll();
        return shoppingList;
    }
}
