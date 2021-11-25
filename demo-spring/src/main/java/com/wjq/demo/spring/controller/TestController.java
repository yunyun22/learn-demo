package com.wjq.demo.spring.controller;

import com.wjq.demo.common.annotation.MyAnnotation;
import com.wjq.demo.config.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjq
 * @since 2021-10-18
 */
@RestController
public class TestController {


    @Autowired
    private Man man;

    @MyAnnotation
    @GetMapping("/hello")
    public String sayHello() {
        return "hello world," + man.getName();
    }
}
