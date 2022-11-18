package com.wjq.demo.spring.service;

import com.wjq.demo.config.Man;
import com.wjq.demo.spring.cache.RedisCacheable;
import org.springframework.stereotype.Service;

@Service
@RedisCacheable
public class ManService {


    /**
     * unsafe
     */
    private int i = 0;


    @RedisCacheable(value = "demo", key = "#id", ttl = "10")
    public Man get(String id) {
        return new Man("王金桥" + i++);
    }
}
