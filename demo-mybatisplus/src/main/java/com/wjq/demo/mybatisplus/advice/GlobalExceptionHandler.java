package com.wjq.demo.mybatisplus.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

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


}
