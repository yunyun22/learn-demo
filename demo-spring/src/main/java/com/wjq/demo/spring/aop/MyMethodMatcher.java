package com.wjq.demo.spring.aop;

import com.wjq.demo.common.annotation.MyAnnotation;
import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

/**
 * @author wjq
 * @since 2021-10-18
 */
public class MyMethodMatcher implements MethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        //注意自定义注解必须使用内置注解@Retention(RetentionPolicy.RUNTIME)
        //不然获取不了
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        //判断方法是否使用了MyAnnotation注解。
        if (annotation != null) return true;
        return false;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }
}
