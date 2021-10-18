package com.wjq.demo.spring.controller;

import com.wjq.demo.common.annotation.MyAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjq
 * @since 2021-10-18
 */
@RestController
public class TestController {

    @MyAnnotation
    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }
}
