package com.wjq.demo.spring.cache;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableCaching
@Import(RedisCachingRegister.class)
public @interface EnableRedisCaching {
}
