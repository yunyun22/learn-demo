package com.wjq.demo.feign.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wjq
 * @since 2022-02-21
 */
public class Params {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
