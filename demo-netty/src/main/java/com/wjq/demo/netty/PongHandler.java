package com.wjq.demo.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Objects;

/**
 * @author wjq
 * @since 2022-03-15
 */
public class PongHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (Objects.equals(msg, "PING")) {
            System.out.println(msg);

            Channel incoming = ctx.channel();
            incoming.writeAndFlush("PONG \n");
            // ctx.fireChannelReadComplete();
        } else {
            ctx.fireChannelRead(msg);
        }
        //ctx.fireChannelInactive();
    }
}
