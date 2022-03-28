package com.wjq.demo.example;

/**
 * @author wjq
 * @since 2022-03-28
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String word) {


        return "hello " + word;
    }
}
