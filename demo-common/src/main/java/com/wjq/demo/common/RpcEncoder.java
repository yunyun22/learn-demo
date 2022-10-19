package com.wjq.demo.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author wjq
 * @since 2022-03-28
 */
public class RpcEncoder extends MessageToByteEncoder {
    private Class<?> clazz;
    private Serializer serializer;

    public RpcEncoder(Class<?> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf byteBuf) throws Exception {
        if (clazz != null && clazz.isInstance(msg)) {
            byte[] bytes = serializer.serialize(msg);
            byteBuf.writeInt(bytes.length);
            byteBuf.writeBytes(bytes);
        }
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime end = LocalDateTime.of(2022, 8, 9, 19, 59, 10);
        LocalDateTime start = LocalDateTime.of(2022, 8, 9, 19, 58, 1);

        long until = start.until(end, ChronoUnit.SECONDS);
        System.out.println(until);
    }
}