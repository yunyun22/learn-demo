package com.wjq.demo.server;

/**
 * @author wjq
 * @since 2022-03-22
 */
public class ServerProperties {
    private int port;

    private String serviceName;

    private String ip;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
