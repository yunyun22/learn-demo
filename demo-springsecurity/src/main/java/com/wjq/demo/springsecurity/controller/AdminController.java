package com.wjq.demo.springsecurity.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/resource")
    public String adminResource(){
        return "resource";
    }
}
