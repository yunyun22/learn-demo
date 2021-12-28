package com.wjq.demo.spring.controller;

import com.wjq.demo.common.domain.User;
import com.wjq.demo.spring.classloader.Metaspace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author wjq
 * @since 2021-12-03
 */
@RestController
public class ErrorController {

    private List<User> userList = new ArrayList<>();

    /**
     * -Xmx32M -Xms32M
     */
    @GetMapping("/heap")
    public String heap() {
        int i = 0;
        while (true) {
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }


    private List<Class<?>> classList = new ArrayList<Class<?>>();

    /**
     * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
     */
    @GetMapping("/nonheap")
    public String nonheap() {
        while (true) {
            classList.addAll(Metaspace.createClasses());
        }
    }


    private Object lock1 = new Object();
    private Object lock2 = new Object();

    /**
     * 死锁
     * */
    @RequestMapping("/deadlock")
    public String deadlock(){
        new Thread(()->{
            synchronized(lock1) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock2) {
                    System.out.println("Thread1 over");
                }
            }
        }) .start();
        new Thread(()->{
            synchronized(lock2) {
                try {Thread.sleep(1000);}catch(Exception e) {}
                synchronized(lock1) {
                    System.out.println("Thread2 over");
                }
            }
        }) .start();
        return "deadlock";
    }

}
