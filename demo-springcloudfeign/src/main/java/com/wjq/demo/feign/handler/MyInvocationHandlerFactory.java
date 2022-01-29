package com.wjq.demo.feign.handler;

import feign.InvocationHandlerFactory;
import feign.Target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author wjq
 * @since 2022-01-28
 */
public class MyInvocationHandlerFactory implements InvocationHandlerFactory {
    @Override
    public InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch) {
        return null;
    }
}
