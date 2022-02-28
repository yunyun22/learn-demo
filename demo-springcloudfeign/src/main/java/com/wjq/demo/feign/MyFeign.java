package com.wjq.demo.feign;

import com.wjq.demo.feign.config.MyFeignConfig;
import com.wjq.demo.feign.dto.Params;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

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
    String sayHello(@SpringQueryMap Params params);


    @DeleteMapping
    String agree(@RequestParam String s,@RequestParam Integer age);

    @PostMapping
    String apply(@RequestBody Params params);
}
