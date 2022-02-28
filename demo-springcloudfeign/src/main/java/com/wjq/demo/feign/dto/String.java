package com.wjq.demo.feign.dto;

/**
 * @author wjq
 * @since 2022-02-25
 */
public class String {

    private final char value[];

    public String(){
        value = new char[1];
    }

    java.lang.String getValue(){
        return new java.lang.String(value);
    }
}
