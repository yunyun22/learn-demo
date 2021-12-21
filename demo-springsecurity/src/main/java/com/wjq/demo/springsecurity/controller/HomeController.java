package com.wjq.demo.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class HomeController {


    @PostMapping("/home")
    public String index(){
        return "this is my home";
    }
}
