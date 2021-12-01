package com.wjq.demo.spring.controller;

import com.wjq.demo.common.annotation.MyAnnotation;
import com.wjq.demo.config.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

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


    @GetMapping("/file")
    public Integer file() {
        boolean flag = false;
        for (int i = 0; i < 1000; i++) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream("./a.txt");

                int read = fileInputStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = fileInputStream != null;
        }
        return flag ? 1 : 0;
    }
}
