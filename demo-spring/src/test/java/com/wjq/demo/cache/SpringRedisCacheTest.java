package com.wjq.demo.cache;

import com.wjq.demo.config.Man;
import com.wjq.demo.spring.SpringTestApplication;
import com.wjq.demo.spring.service.ManService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringTestApplication.class)
public class SpringRedisCacheTest {


    @Autowired
    private Man man;

    @Autowired
    private ManService manService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testManCache() {

        stringRedisTemplate.delete("demo::test");

        Set<String> keys = stringRedisTemplate.keys("*");
        System.out.println(keys);
        Man test = manService.get("test");

        keys = stringRedisTemplate.keys("*");
        System.out.println(keys);
        Assert.assertNotNull(keys);
        Assert.assertTrue(!keys.isEmpty());

        Long expire = stringRedisTemplate.getExpire("demo::test");
        System.out.println(expire);
    }


}
