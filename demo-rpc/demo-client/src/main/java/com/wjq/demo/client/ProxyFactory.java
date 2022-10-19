package com.wjq.demo.client;

import com.wjq.demo.register.ServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * @author wjq
 * @since 2022-03-28
 */
public class ProxyFactory {
    public static <T> T create(Class<T> interfaceClass, ServiceDiscovery serviceDiscovery) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new RpcClientDynamicProxy<T>(interfaceClass, serviceDiscovery));
    }
}
