package com.wjq.demo.feign.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wjq
 * @since 2022-09-02
 */
public class InfoFeignLogger extends feign.Logger {

    private static final Logger logger = LoggerFactory.getLogger(InfoFeignLogger.class);

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isInfoEnabled()) {
            logger.info(String.format(methodTag(configKey) + format, args));
        }
    }
}
