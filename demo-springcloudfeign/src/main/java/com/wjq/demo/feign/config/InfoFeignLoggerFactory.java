package com.wjq.demo.feign.config;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

/**
 * @author wjq
 * @since 2022-09-02
 */
public class InfoFeignLoggerFactory implements FeignLoggerFactory {
    @Override
    public Logger create(Class<?> type) {
        return new InfoFeignLogger();
    }
}
