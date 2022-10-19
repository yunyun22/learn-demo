package com.wjq.demo.spring.error.handler;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author wjq
 * @since 2022-01-19
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e, WebRequest request) {
        System.out.println("this is debug " + e.getMessage());
        return ResponseEntity.status(200).body("It is ExceptionHandler");
    }
}
