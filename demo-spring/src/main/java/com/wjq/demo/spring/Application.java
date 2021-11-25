package com.wjq.demo.spring;


import com.wjq.demo.config.Test2;
import com.wjq.demo.config.TestConfig;
import com.wjq.demo.spring.config.ManConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

/**
 * @author wjq
 * @since 2021-10-15
 */
@ComponentScan(basePackages = { "com.wjq.demo" },
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern="com.wjq.demo.config.Test2*"))
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
