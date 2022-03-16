package com.wjq.demo.netty;

import io.netty.channel.Channel;

/**
 * @author wjq
 * @since 2022-03-15
 */
public class BeatRunnable implements Runnable{

    private Channel channel;

    public BeatRunnable(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        channel.writeAndFlush("PING" + "\r\n");
    }
}
