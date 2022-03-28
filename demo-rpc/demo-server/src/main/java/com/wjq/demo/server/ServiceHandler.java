package com.wjq.demo.server;

import com.wjq.demo.common.RpcRequest;
import net.sf.cglib.reflect.FastClass;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjq
 * @since 2022-03-25
 */

public class ServiceHandler {

    private static final Map<Class<?>, FastClass> CACHE = new ConcurrentHashMap<>();

    private static final ServiceManager SERVICE_MANAGER = ServiceManager.INSTANCE;

    public Object handler(RpcRequest request) {
        String className = request.getClassName();
        Object serviceBean = SERVICE_MANAGER.get(className);
        if (serviceBean == null) {
            return null;
        }

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        FastClass fastClass = get(serviceClass);
        int methodIndex = fastClass.getIndex(methodName, parameterTypes);
        try {
            return fastClass.invoke(methodIndex, serviceBean, parameters);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("service 执行失败", e);
        }
    }


    private FastClass get(Class<?> serviceClass) {
        FastClass fastClass = CACHE.get(serviceClass);
        if (fastClass != null) {
            return fastClass;
        }

        FastClass serviceFastClass = FastClass.create(serviceClass);
        CACHE.put(serviceClass, serviceFastClass);
        return serviceFastClass;
    }
}
