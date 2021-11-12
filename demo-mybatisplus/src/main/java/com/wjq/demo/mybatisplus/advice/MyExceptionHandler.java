package com.wjq.demo.mybatisplus.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wjq
 * @since 2021-10-12
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object customHandler(RuntimeException e) {
        e.printStackTrace();
        return "error";
    }

}
