package com.wjq.demo.shiro.aspect;

import com.wjq.demo.shiro.annotation.RequestMappingAuth;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wjq
 * @since 2021-10-11
 */
@Aspect
@Component
public class AuthAspect {


    private final static Map<String, List<String>> CACHE = new ConcurrentHashMap<>();

    public static final List<String> PATHS = new ArrayList<>();

    static {
        PATHS.add("GET /hello/*");
    }

    @Pointcut("execution(public * com.wjq.demo.shiro.controller.*.*(..))")
    public void auth() {
    }

    private final Pattern pattern = Pattern.compile("\\{.*?\\}");

    @Before("auth()")
    public void doBefore(JoinPoint joinPoint) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Set<RequestMapping> classRequestMapping = AnnotatedElementUtils.findAllMergedAnnotations(joinPoint.getTarget().getClass(), RequestMapping.class);
        if (classRequestMapping.size() != 0) {
            RequestMapping requestMapping = classRequestMapping.stream().findFirst().get();
            String[] values = requestMapping.value();
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取当前切点方法对象
        RequestMappingAuth annotation = methodSignature.getMethod().getAnnotation(RequestMappingAuth.class);
        if (annotation != null) {
            Set<RequestMapping> allMergedAnnotations =
                    AnnotatedElementUtils.findAllMergedAnnotations(methodSignature.getMethod(), RequestMapping.class);

            for (RequestMapping requestMapping : allMergedAnnotations) {
                String path = requestMapping.value()[0];
                Matcher matcher = pattern.matcher(path);
                path = matcher.replaceAll("*");
                String method = requestMapping.method()[0].name();
                if (PATHS.contains(method + " " + path)) {
                    stopWatch.stop();
                    System.out.println(stopWatch.prettyPrint());
                    return;
                }
            }
            throw new RuntimeException("没有权限");
        }
    }

}
