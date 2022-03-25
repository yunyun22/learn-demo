package com.wjq.demo.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author wjq
 * @since 2022-03-22
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {


    private final ServiceHandler serviceHandler;

    public NettyServerHandler(ServiceHandler serviceHandler) {
        this.serviceHandler = serviceHandler;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());
        try {
            Object result = serviceHandler.handler(rpcRequest);
            rpcResponse.setResult(result);
        } catch (Exception e) {
            rpcResponse.setError(e.getMessage());
        }
        ctx.writeAndFlush(rpcResponse);
    }
}
