package com.wjq.demo.server;

/**
 * @author wjq
 * @since 2022-03-22
 */
public interface Server {

    /**
     * 启动
     *
     * @param serverProperties 启动参数
     */
    void start(ServerProperties serverProperties);

    /**
     * stop
     */
    void stop();

}
