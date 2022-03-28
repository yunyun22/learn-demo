package com.wjq.demo.client;


import com.wjq.demo.common.RpcRequest;
import com.wjq.demo.common.RpcResponse;
import io.netty.channel.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wjq
 * @since 2022-03-28
 */
public class ClientHandler extends SimpleChannelInboundHandler<RpcResponse> {
    /**
     * 使用Map维护请求对象ID与响应结果Future的映射关系
     */
    private final Map<String, DefaultFuture> futureMap = new ConcurrentHashMap<>();
    private volatile Channel channel;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
        if (msg instanceof RpcResponse) {
            //获取响应对象
            RpcResponse response = (RpcResponse) msg;
            DefaultFuture defaultFuture =
                    futureMap.get(response.getRequestId());
            //将结果写入DefaultFuture
            defaultFuture.setResponse(response);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    public void sendRequest(RpcRequest request) {
        try {
            ChannelFuture sync = channel.writeAndFlush(request).sync();
            if (sync.isSuccess()) {
                futureMap.put(request.getRequestId(), new DefaultFuture());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("发送失败", e);
        }
    }

    /**
     * 获取响应结果
     *
     * @param requsetId
     * @return
     */
    public RpcResponse getRpcResponse(String requsetId) {
        try {
            DefaultFuture future = futureMap.get(requsetId);
            return future.getRpcResponse(5000);
        } finally {
            //获取成功以后，从map中移除
            futureMap.remove(requsetId);
        }
    }
}
