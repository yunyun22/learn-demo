package com.wjq.demo.shiro.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler(){
        System.out.println("初始化 GlobalExceptionHandler");
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public Object customHandler(SQLIntegrityConstraintViolationException e) {

        String sqlState = e.getSQLState();
        String message = e.getMessage();
        String localizedMessage = e.getLocalizedMessage();
        int errorCode = e.getErrorCode();
        e.printStackTrace();
        return String.format("sqlState:%s,message:%s,localizedMessage:%s,errorCode:%s", sqlState, message, localizedMessage, errorCode);

    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object customHandlerException(Exception e) {

        e.printStackTrace();
        return "exception error";

    }

}
