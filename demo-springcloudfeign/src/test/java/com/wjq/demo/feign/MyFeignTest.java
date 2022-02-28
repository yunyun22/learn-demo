package com.wjq.demo.feign;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wjq
 * @since 2022-02-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class MyFeignTest {


    @Autowired
    private MyFeign myFeign;

    @Test
    void agree() {
        myFeign.agree("1", 2);
    }
}