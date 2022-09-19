package com.wjq.demo.feign.config;

import com.wjq.demo.feign.intecepter.ProxyInterceptor;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wjq
 * @since 2021-12-23
 */
public class MyFeignConfig implements ApplicationContextAware {


    private static final int CONNECT_TIME_OUT_MILLIS = 3000;
    private static final int READ_TIME_OUT_MILLIS = 3000;
    private ApplicationContext applicationContext;

    @Bean
    public Request.Options options() {
        return new Request.Options(CONNECT_TIME_OUT_MILLIS, TimeUnit.MILLISECONDS, READ_TIME_OUT_MILLIS, TimeUnit.MILLISECONDS, true);
    }

    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;
    }


    @Bean
    public ProxyInterceptor proxyInterceptor() {
        ApplicationContext parent = applicationContext.getParent();
        if (parent != null) {
            Map<String, ProxyInterceptor> beansOfType = parent.getBeansOfType(ProxyInterceptor.class);
            if (beansOfType.isEmpty()){
                System.out.println("ProxyInterceptor is empty");
            }
            System.out.println("再次代理");
        }

        return new ProxyInterceptor();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
