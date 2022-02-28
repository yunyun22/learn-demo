package com.wjq.demo.feign.dto;

/**
 * @author wjq
 * @since 2022-02-25
 */
public class MyString  extends String{

    private char value[];

    @Override
    java.lang.String getValue(){
        return new java.lang.String(value);
    }
}
