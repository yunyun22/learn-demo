package com.wjq.demo.feign;

import com.wjq.demo.feign.base.MultiRequestParam;
import com.wjq.demo.feign.config.MyFeignConfig;
import com.wjq.demo.feign.dto.Params;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

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


    @PutMapping("/test1")
    String test1(@MultiRequestParam Params params);


    @PutMapping("/test2/{map}")
    String test2(@MatrixVariable Map<String,String> map);
}
