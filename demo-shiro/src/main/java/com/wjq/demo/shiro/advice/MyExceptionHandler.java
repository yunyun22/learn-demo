package com.wjq.demo.shiro.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wjq
 * @since 2021-10-12
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyExceptionHandler {

    public MyExceptionHandler() {
        System.out.println("初始化MyExceptionHandler");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object customHandler(RuntimeException e) {
        e.printStackTrace();
        return "error";
    }

}
