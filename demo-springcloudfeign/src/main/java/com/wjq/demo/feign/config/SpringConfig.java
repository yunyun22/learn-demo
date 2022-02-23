package com.wjq.demo.feign.config;

import com.wjq.demo.common.util.SpringBeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wjq
 * @since 2022-02-22
 */
@Configuration
public class SpringConfig {

    @Bean
    public SpringBeanUtils getSpringBeanUtils(){
        return new SpringBeanUtils();
    }
}
