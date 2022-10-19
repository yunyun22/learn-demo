package com.wjq.demo.common;


import com.alibaba.fastjson.JSON;

/**
 * @author wjq
 * @since 2022-03-28
 */
public class JSONSerializer implements Serializer{

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}