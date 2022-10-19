package com.wjq.demo.register;

/**
 * @author wjq
 * @since 2022-03-24
 */
public class Server {

    private String ip;
    private String port;
    private String serviceName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    @Override
    public String toString() {
        return "Server{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
