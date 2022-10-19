package com.wjq.demo.server;

import com.wjq.demo.common.*;
import com.wjq.demo.register.RegisterInfo;
import com.wjq.demo.register.ServiceRegister;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wjq
 * @since 2022-03-22
 */
public class NettyServer implements Server {

    private final ServiceRegister serviceRegister;
    private final ServerProperties serverProperties;

    public NettyServer(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
        Properties properties = new Properties();
        properties.setProperty("zookeeper.serverAddress", serverProperties.getServerAddress());
        properties.setProperty("zookeeper.timeout", serverProperties.getSessionTimeout());

        try {
            this.serviceRegister = new ServiceRegister(properties);
        } catch (IOException e) {
            throw new RuntimeException("启动失败");
        }
    }

    @Override
    public void register(Class<?> aClass, Object o) {
        ServiceManager.INSTANCE.register(aClass.getName(), o);
        ServiceRPC annotation = aClass.getAnnotation(ServiceRPC.class);
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setIp(serverProperties.getIp());
        registerInfo.setPort(Integer.toString(serverProperties.getPort()));
        registerInfo.setServiceName(annotation.serviceName());
        serviceRegister.register(registerInfo);
    }

    @Override
    public void start() throws IOException {
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
                            pipeline.addLast(new RpcDecoder(RpcRequest.class, new JSONSerializer()));
                            pipeline.addLast(new RpcEncoder(RpcResponse.class, new JSONSerializer()));
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
    }

    @Override
    public void stop() {

    }


}
