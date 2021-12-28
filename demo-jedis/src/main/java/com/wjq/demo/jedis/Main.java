package com.wjq.demo.jedis;

import redis.clients.jedis.*;

/**
 * @author wjq
 * @since 2021-11-26
 */
public class Main {


    public static void main(String[] args) {

        //1 获得连接池配置对象，设置配置项
        JedisPoolConfig config = new JedisPoolConfig();
        // 1.1 最大连接数
        config.setMaxTotal(30);

        //1.2 最大空闲连接数
        config.setMaxIdle(10);

        JedisPool jedisPool = new JedisPool(config,"139.155.73.132",6379);

        Jedis jedis = jedisPool.getResource();
        String set1 = jedis.set("test", "abc");

        System.out.println("set1===========" + set1);

        Pipeline pipelined = jedis.pipelined();

        Response<String> test = pipelined.get("test");
        Response<String> set = pipelined.set("test2", "ccc");
        Response<String> test2 = pipelined.get("test2");
        pipelined.sync();

        System.out.println("test===================" + test.get());
        System.out.println("set===================" + set.get());
        System.out.println("test2===================" + test2.get());

        Transaction multi = jedis.multi();
    }
}
