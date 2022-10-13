package com.wjq.demo.feign;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author wjq
 * @since 2022-02-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class MyFeignTest {


    @Autowired
    private DemoFeign demoFeign;

    @Test
    void agree() {
        String s = demoFeign.sayHello();
        System.out.println(s);
    }
}