package com.wjq.demo.example;

import com.wjq.demo.server.NettyServer;
import com.wjq.demo.server.ServerProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author wjq
 * @since 2022-03-28
 */
@Slf4j
public class ApplicationServer {

    public static void main(String[] args) throws InterruptedException {

        ServerProperties serverProperties = ServerProperties.builder()
                .port(10999)
                .serverAddress("139.155.73.132:2181")
                .sessionTimeout("10000")
                .ip("127.0.0.1").build();
        NettyServer nettyServer = new NettyServer(serverProperties);
        nettyServer.register(HelloService.class, new HelloServiceImpl());

        Thread thread = new Thread(() -> {
            try {
                nettyServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        thread.start();
        thread.join();
    }
}
