package com.wjq.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test2 {

    @Bean
    public Man getMan(){
        return new Man("www");
    }
}
