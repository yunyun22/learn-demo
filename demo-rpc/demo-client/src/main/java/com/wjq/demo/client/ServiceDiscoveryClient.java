package com.wjq.demo.client;

import com.wjq.demo.common.RpcRequest;
import com.wjq.demo.common.RpcResponse;
import com.wjq.demo.register.Server;
import com.wjq.demo.register.ServiceDiscovery;

import java.util.List;

/**
 * @author wjq
 * @since 2022-03-28
 */
public class ServiceDiscoveryClient implements Client {

    private Client client;


    public ServiceDiscoveryClient(String serviceName, ServiceDiscovery serviceDiscovery) {
        List<Server> servers = serviceDiscovery.get(serviceName);
        Server server = servers.get(0);
        this.client = new NettyClient(server.getIp(), Integer.valueOf(server.getPort()));
    }

    @Override
    public void connect() {
        client.connect();
    }

    @Override
    public RpcResponse send(RpcRequest request) {
        return client.send(request);
    }
}
