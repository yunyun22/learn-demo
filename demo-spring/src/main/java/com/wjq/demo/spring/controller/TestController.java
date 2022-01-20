package com.wjq.demo.spring.controller;

import com.wjq.demo.common.annotation.MyAnnotation;
import com.wjq.demo.config.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wjq
 * @since 2021-10-18
 */
@RestController
public class TestController {


    @Autowired
    private Man man;


    @GetMapping("/doError")
    public void doError() throws Exception {
        throw new Exception("this is a Exception");
    }

    @MyAnnotation
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(HttpServletRequest req) {
        DispatcherType dispatcherType = req.getDispatcherType();
        if (dispatcherType.equals(DispatcherType.FORWARD)){
            System.out.println("this is forward");
        }
        System.out.println(req);
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

    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("start forward");
        return "forward:/hello";
    }
}
