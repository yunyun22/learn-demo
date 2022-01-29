package com.wjq.demo.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjq
 * @since 2022-01-29
 */
@RefreshScope
@RestController
public class TestController {


    @Value("${user.name}")
    private String name;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello " + name;
    }

}
