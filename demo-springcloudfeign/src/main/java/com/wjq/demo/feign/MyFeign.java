package com.wjq.demo.feign;

import com.wjq.demo.feign.config.MyFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wjq
 * @since 2021-12-23
 */
@FeignClient(name = "myFeign", url = "localhost:8080", path = "/hello",configuration = MyFeignConfig.class)
public interface MyFeign {

    /**
     * 返回hello world
     *
     * @return hello world
     */
    @GetMapping
    String sayHello();
}
