package com.wjq.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Test2.class)
public class TestConfig {
}
