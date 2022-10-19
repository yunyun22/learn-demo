package com.wjq.demo.server;

import com.wjq.demo.common.RpcRequest;

/**
 * @author wjq
 * @since 2022-03-22
 */
public interface ServerHandler {

    /**
     * server方法处理器
     * @param args 参数列表
     * @return 结果
     */
    Object handler(RpcRequest request);
}
