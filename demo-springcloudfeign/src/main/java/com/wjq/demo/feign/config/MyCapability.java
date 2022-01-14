package com.wjq.demo.feign.config;

import feign.Capability;
import feign.Logger;

/**
 * @author wjq
 * @since 2022-01-13
 */
public class MyCapability implements Capability {

    @Override
    public Logger.Level enrich(Logger.Level level) {
        System.out.println("this is " + level);
        return Capability.super.enrich(level);
    }


    @Override
    public Logger enrich(Logger logger) {
        System.out.println("this is " + logger);
        return Capability.super.enrich(logger);
    }
}
