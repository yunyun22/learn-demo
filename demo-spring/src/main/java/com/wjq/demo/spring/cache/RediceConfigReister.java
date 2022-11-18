package com.wjq.demo.spring.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RediceConfigReister {

    private Map<String, CacheConfig> map = new ConcurrentHashMap<>();


    public void put(String name, CacheConfig cacheConfig) {
        map.put(name, cacheConfig);
    }
}
