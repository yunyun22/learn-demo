package com.wjq.demo.feign;

import com.wjq.demo.common.util.SpringBeanUtils;
import com.wjq.demo.feign.dto.Params;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author wjq
 * @since 2021-12-23
 */
@SpringBootApplication
@EnableFeignClients
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
        String s= "a";
        s.length();
    }
}
