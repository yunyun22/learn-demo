package com.wjq.demo.mybatisplus.aspect;

import com.wjq.demo.mybatisplus.annotation.RequestMappingAuth;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wjq
 * @since 2021-10-11
 */
@Aspect
@Component
public class AuthAspect {

    public static final List<String> PATHS = new ArrayList<>();

    {
        PATHS.add("GET /hello/*");
    }

    @Pointcut("execution(public * com.wjq.demo.mybatisplus.controller.*.*(..))")
    public void auth() {
    }

    Pattern pattern = Pattern.compile("\\\\\\{*\\\\}");
    @Before("auth()")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取当前切点方法对象
        RequestMappingAuth annotation = methodSignature.getMethod().getAnnotation(RequestMappingAuth.class);
        if (annotation != null) {
            Set<RequestMapping> allMergedAnnotations = AnnotatedElementUtils.findAllMergedAnnotations(methodSignature.getMethod(), RequestMapping.class);

            for (RequestMapping requestMapping : allMergedAnnotations) {
                String path = requestMapping.value()[0];
                Matcher matcher = pattern.matcher(path);
                path = matcher.replaceAll("*");
                String method = requestMapping.method()[0].name();
                if (!Objects.equals(PATHS.get(0), method + " " + path)) {
                    throw new RuntimeException("没有权限");
                }
            }
        }
    }


}
