package com.wjq.demo.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anonymous")
public class AnonymousController {


    @RequestMapping("/resource")
    public String commonResources(){
        return "resource";
    }
}
