package com.wjq.demo.feign.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author wjq
 * @since 2021-12-23
 */
public class MyFeignConfig {


    private static final int CONNECT_TIME_OUT_MILLIS = 3000;
    private static final int READ_TIME_OUT_MILLIS = 3000;

    @Bean
    public Request.Options options() {
        return new Request.Options(CONNECT_TIME_OUT_MILLIS, TimeUnit.MILLISECONDS, READ_TIME_OUT_MILLIS, TimeUnit.MILLISECONDS, true);
    }

    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }
}
