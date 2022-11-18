package com.wjq.demo.spring;


import com.wjq.demo.spring.cache.EnableRedisCaching;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author wjq
 * @since 2021-10-15
 */
@SpringBootApplication
@EnableCaching
@EnableRedisCaching
public class SpringTestApplication {
    public static void main(String[] args) {
        System.currentTimeMillis();
        long l = System.currentTimeMillis()/1000L;
        System.out.println(l);
        //-Xmx32M -XX:+UseG1GC -Xms32M  -XX:+PrintGCDetails -Xloggc:./gc.log -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
        //org.springframework.boot.SpringApplication.run(SpringTestApplication.class, args);
    }
}
