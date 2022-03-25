package com.wjq.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wjq
 * @since 2022-03-22
 */
public class NettyServer implements Server {

    @Override
    public void start(ServerProperties serverProperties) {
        new Thread(() -> {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<Channel>() {
                            @Override
                            protected void initChannel(Channel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                pipeline.addLast(new NettyServerHandler(new ServiceHandler()));
                            }
                        })
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .childOption(ChannelOption.SO_KEEPALIVE, true);


                // 绑定端口，开始接收进来的连接
                ChannelFuture f = b.bind(serverProperties.getPort()).sync();

                // 等待服务器  socket 关闭 。
                // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
                f.channel().closeFuture().sync();

            } catch (InterruptedException e) {




            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();


            }

        });
    }

    @Override
    public void stop() {

    }


}
