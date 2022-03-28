package com.wjq.demo.client;

import com.wjq.demo.common.RpcRequest;
import com.wjq.demo.common.RpcResponse;

/**
 * @author wjq
 * @since 2022-03-28
 */
public interface Client {

     void connect();

    RpcResponse send(final RpcRequest request);
}
