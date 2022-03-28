package com.wjq.demo.example;

import com.wjq.demo.common.ServiceRPC;

/**
 * @author wjq
 * @since 2022-03-28
 */
@ServiceRPC(serviceName = "helloService")
public interface HelloService {

    /**
     * hello word
     *
     * @param word word
     */
    String say(String word);
}
