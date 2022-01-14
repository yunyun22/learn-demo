package com.wjq.demo.feign.controller;

import com.wjq.demo.feign.MyFeign;
import com.wjq.demo.feign.client.GitHub;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wjq
 * @since 2021-12-23
 */
@RestController
public class MyFeignController {

    @Autowired
    private MyFeign myFeign;

    @GetMapping("/hello")
    public String sayHello() {
        return myFeign.sayHello();
    }

    @GetMapping("/contributors")
    public List<GitHub.Contributor> contributors() {
        GitHub github = Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        /* The owner and repository parameters will be used to expand the owner and repo expressions
         * defined in the RequestLine.
         *
         * the resulting uri will be https://api.github.com/repos/OpenFeign/feign/contributors
         */
        return github.contributors("OpenFeign", "feign");
    }
}
