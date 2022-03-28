package com.wjq.demo.server;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wjq
 * @since 2022-03-22
 */
@Builder
@Getter
@Setter
public class ServerProperties {
    private int port;

    private String ip;

    private  String serverAddress;

    private  String sessionTimeout;

}
