package com.labs.vsu.labProj.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.vsu.labProj.entity.*;
import com.labs.vsu.labProj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/rest/users")
public class UserController {

    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/save", method = RequestMethod.POST,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String saveUser(@RequestBody String user){
        logger.info(user);
        User requestUser;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            requestUser = mapper.readValue(user, User.class);
//            User newUser = new User(requestUser.getUserFirstName(),
//                                    requestUser.getUserLastName(),
//                                    requestUser.getUserEmail(),
//                                    requestUser.getUserPassword());
            logger.info(requestUser.toString());
            userService.save(requestUser);
            } catch (IOException e) {
                e.printStackTrace();
        }
        return "User was successfully saved";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String updateUser(@RequestBody String user){
        ObjectMapper mapper = new ObjectMapper();
        User requestUser;
        try {
            requestUser = mapper.readValue(user, User.class);
            logger.info("request:"+requestUser.toString());

            User oldUser = userService.findByUserEmail(requestUser.getUserEmail());
            logger.info("found: " + oldUser.toString());

            oldUser.setUserFirstName(requestUser.getUserFirstName());
            oldUser.setUserLastName(requestUser.getUserLastName());
            oldUser.setUserPassword(requestUser.getUserPassword());
            oldUser.setShoppingList(requestUser.getShoppingList());
            userService.save(oldUser);
            logger.info("updated: " + oldUser.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "User was successfully updated";
    }

    @RequestMapping(value = "/delete/{userEmail}", method = RequestMethod.DELETE,  produces = { MediaType.APPLICATION_JSON_VALUE, //
            MediaType.APPLICATION_XML_VALUE})
    public String deleteUser(@PathVariable("userEmail") String userEmail){
        logger.info(userEmail);
        userService.deleteByUserEmail(userEmail);
        return "User was deleted";
    }

//    @CrossOrigin
//    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE,  produces = { MediaType.APPLICATION_JSON_VALUE, //
//            MediaType.APPLICATION_XML_VALUE})
//    public String deleteUser(@PathVariable("userId") long userId){
//        logger.info(""+userId);
//        userService.deleteById(userId);
//        return "User was deleted";
//    }

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<User> findAll(){
        List<User> users = userService.findAll();
        return users;
    }

    @RequestMapping(value="/findbyid", method = RequestMethod.GET)
    public String findById(@RequestParam("userId") long userId){ //@RequestParam("user_id")
         return userService.findByUserId(userId).toString();
    }

    @RequestMapping(value="/findbylastname", method = RequestMethod.GET)
    public String findByLastName(@RequestParam("userLastName") String userLastName){ //@RequestParam("user_last_name") //?
        return userService.findByUserLastName(userLastName).toString();
    }

}
