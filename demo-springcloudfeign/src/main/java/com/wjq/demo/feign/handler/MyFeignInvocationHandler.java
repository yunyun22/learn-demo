package com.wjq.demo.feign.handler;

import feign.ReflectiveFeign;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wjq
 * @since 2022-01-28
 */
public class MyFeignInvocationHandler  implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return  null;
    }
}
