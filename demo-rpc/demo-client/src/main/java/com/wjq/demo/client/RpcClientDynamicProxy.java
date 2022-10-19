package com.wjq.demo.client;

import com.wjq.demo.common.RpcRequest;
import com.wjq.demo.common.RpcResponse;
import com.wjq.demo.common.ServiceRPC;
import com.wjq.demo.register.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @author wjq
 * @since 2022-03-28
 */
@Slf4j
public class RpcClientDynamicProxy<T> implements InvocationHandler {
    private Class<T> clazz;
    private ServiceDiscovery serviceDiscovery;

    public RpcClientDynamicProxy(Class<T> clazz, ServiceDiscovery serviceDiscovery) throws Exception {
        this.serviceDiscovery = serviceDiscovery;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        String requestId = UUID.randomUUID().toString();

        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        Class<?>[] parameterTypes = method.getParameterTypes();

        request.setRequestId(requestId);
        request.setClassName(className);
        request.setMethodName(methodName);
        request.setParameterTypes(parameterTypes);
        request.setParameters(args);
        log.info("请求内容: {}", request);

        ServiceRPC annotation = clazz.getAnnotation(ServiceRPC.class);
        //开启Netty 客户端，直连
        Client client = new ServiceDiscoveryClient(annotation.serviceName(), serviceDiscovery);
        log.info("开始连接服务端：{}", new Date());
        client.connect();
        RpcResponse send = client.send(request);
        log.info("请求调用返回结果：{}", send.getResult());
        return send.getResult();
    }
}