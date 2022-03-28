package com.wjq.demo.server;

import java.io.IOException;

/**
 * @author wjq
 * @since 2022-03-22
 */
public interface Server {


    /**
     * 注册service
     *
     * @param aClass class
     * @param o      service对象
     */
    void register(Class<?> aClass, Object o);

    /**
     * 启动
     */
    void start() throws IOException;

    /**
     * stop
     */
    void stop();

}
