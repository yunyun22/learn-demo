package com.wjq.demo.feign;

import com.wjq.demo.feign.config.MyFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wjq
 * @since 2022-09-30
 */
@FeignClient(name = "demo-nacos", configuration = MyFeignConfig.class, contextId = "demo-feign")
public interface DemoFeign {


    /**
     * say hello
     *
     * @return say hello
     */
    @GetMapping("/hello")
    String sayHello();
}
