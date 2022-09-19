package com.wjq.demo.feign.intecepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;


/**
 * @author wjq
 * @since 2022-09-19
 */
public class ProxyInterceptor implements RequestInterceptor, EnvironmentAware {

    private Environment environment;

    @Override
    public void apply(RequestTemplate template) {

        String[] activeProfiles = environment.getActiveProfiles();
        Target<?> target = template.feignTarget();

        try {
            Field url = target.getClass().getDeclaredField("url");
            url.setAccessible(true);
            url.set(target, "https://www.baidu.com");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            System.err.println("error");
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
