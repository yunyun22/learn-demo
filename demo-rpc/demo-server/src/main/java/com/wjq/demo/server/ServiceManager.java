package com.wjq.demo.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjq
 * @since 2022-03-25
 */
public class ServiceManager {


    private ServiceManager() {
    }


    public final static ServiceManager INSTANCE = new ServiceManager();

    private final Map<String, Object> services = new ConcurrentHashMap<>();


    public void register(String claasName, Object service) {
        services.put(claasName, service);
    }


    public Object get(String className) {
        return services.get(className);
    }
}
