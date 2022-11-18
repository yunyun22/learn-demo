package com.wjq.demo.spring;

import com.wjq.demo.spring.cache.CacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class MyRedisCacheManagerBuilder implements RedisCacheManagerBuilderCustomizer {


    @Autowired
    private List<CacheConfig> list;

    //first collete info

    //second

    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {


        if (list == null || list.isEmpty()) {
            return;
        }


        for (CacheConfig cacheConfig : list) {

            String[] name = cacheConfig.getName();

            Optional<RedisCacheConfiguration> cacheConfigurationFor = builder.getCacheConfigurationFor(name[0]);
            RedisCacheConfiguration redisCacheConfiguration = cacheConfigurationFor.orElseGet(() -> {
                HashSet<String> set = new HashSet<>();
                set.add(name[0]);
                builder.initialCacheNames(set);
                return builder.getCacheConfigurationFor(name[0]).orElseGet(RedisCacheConfiguration::defaultCacheConfig);
            });

            long ttl = Long.parseLong(cacheConfig.getTtl());
            redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(ttl));
            builder.withCacheConfiguration(name[0], redisCacheConfiguration);
        }


    }
}
