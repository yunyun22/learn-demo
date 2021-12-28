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

/**
 * @author wjq
 * @since 2021-10-18
 */
@Controller
public class TestController {


    @Autowired
    private Man man;

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

    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("start forward");
        return "forward:/hello";
    }
}
