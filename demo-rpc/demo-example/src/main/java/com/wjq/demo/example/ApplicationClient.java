package com.wjq.demo.example;

import com.wjq.demo.client.ProxyFactory;
import com.wjq.demo.register.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author wjq
 * @since 2022-03-28
 */
@Slf4j
public class ApplicationClient {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("zookeeper.serverAddress", "139.155.73.132:2181");
        properties.setProperty("zookeeper.timeout", "10000");
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery(properties);

        HelloService helloService = ProxyFactory.create(HelloService.class, serviceDiscovery);
        for (int i = 0; i < 10; i++) {
            String helloWorld = helloService.say("world ");
            log.info(helloWorld);
        }

    }
}
