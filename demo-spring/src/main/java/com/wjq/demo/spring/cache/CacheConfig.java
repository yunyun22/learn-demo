package com.wjq.demo.spring.cache;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class CacheConfig {


    @Override
    public String toString() {
        return "CacheConfig{" +
                "ttl='" + ttl + '\'' +
                ", ttlType='" + ttlType + '\'' +
                ", name=" + Arrays.toString(name) +
                '}';
    }

    public CacheConfig(String ttl, String ttlType, String[] name) {
        this.ttl = ttl;
        this.ttlType = ttlType;
        this.name = name;
    }


    public String getTtl() {
        return ttl;
    }

    public String getTtlType() {
        return ttlType;
    }

    public String[] getName() {
        return name;
    }

    private String ttl;

    private String ttlType;

    private String[] name;


}
