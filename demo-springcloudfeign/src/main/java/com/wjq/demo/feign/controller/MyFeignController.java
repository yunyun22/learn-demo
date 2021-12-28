package com.wjq.demo.feign.controller;

import com.wjq.demo.feign.MyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wjq
 * @since 2021-12-23
 */
@RestController
public class MyFeignController {


    @Autowired
    private MyFeign myFeign;

    @GetMapping("/hello")
    public String sayHello(){
        return myFeign.sayHello();
    }
}
