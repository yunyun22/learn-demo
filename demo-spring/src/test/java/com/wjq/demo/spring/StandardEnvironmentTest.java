package com.wjq.demo.spring;

import com.wjq.demo.spring.env.MyStandardEnvironment;
import org.junit.Test;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wjq
 * @since 2022-01-20
 */
public class StandardEnvironmentTest {


    @Test
    public void test(){
        Map<String, Object> map = new HashMap<>();
        map.put("user.name","wangjq");
        map.put("user.age","19");
        MyStandardEnvironment standardEnvironment = new MyStandardEnvironment(new MapPropertySource("myMapPropertySource",map));


        String s = standardEnvironment.getProperty("user.age");
        System.out.println(s);
    }

}
