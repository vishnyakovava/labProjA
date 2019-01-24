package com.labs.vsu.labProj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping(value = "/userTable", method = RequestMethod.GET)
    public String userPage(){
        return "index.html";
    }

    @RequestMapping(value = "/purchaseTable", method = RequestMethod.GET)
    public String purchasePage(){
        return "purchase.html";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String signInUpPage(){
        return "sign.html";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(){
        return "main.html";
    }

}