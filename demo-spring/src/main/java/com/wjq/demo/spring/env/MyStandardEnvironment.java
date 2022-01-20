package com.wjq.demo.spring.env;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

import java.util.HashMap;

/**
 * @author wjq
 * @since 2022-01-20
 */
public class MyStandardEnvironment  extends StandardEnvironment {


    public MyStandardEnvironment( MapPropertySource mapPropertySource){
        super();
        super.getPropertySources().addLast(mapPropertySource);
    }

}
