package com.wjq.demo.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author wjq
 * @since 2022-02-22
 */
@SuppressWarnings("unchecked")
public class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        if (applicationContext.containsBean(beanName)) {
            return (T) applicationContext.getBean(beanName);
        } else {
            return null;
        }
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> baseType) {
        return applicationContext.getBeansOfType(baseType);
    }
}

