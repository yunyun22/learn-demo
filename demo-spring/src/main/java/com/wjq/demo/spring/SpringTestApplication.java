package com.wjq.demo.spring;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * @author wjq
 * @since 2021-10-15
 */
@SpringBootApplication
public class SpringTestApplication {
    public static void main(String[] args) {
        System.currentTimeMillis();
        long l = System.currentTimeMillis()/1000L;
        System.out.println(l);
        //-Xmx32M -XX:+UseG1GC -Xms32M  -XX:+PrintGCDetails -Xloggc:./gc.log -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
        //org.springframework.boot.SpringApplication.run(SpringTestApplication.class, args);
    }
}
