package com.my.model;

import com.my.model.RpcProxyHandler;
import com.my.model.RpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

public class MyDubboProxy {
    public <T> T Create(final Class<T> interfaceClass) {
        Object o = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, args) -> {
            CountDownLatch latch= new CountDownLatch(1);
            RpcRequest request = new RpcRequest();
            request.setClassName(interfaceClass.getName());
            request.setMethodName(method.getName());
            request.setParams(args);
            request.setTypes(method.getParameterTypes());
            RpcProxyHandler handler = new RpcProxyHandler();
            NioEventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<Channel>() {
                            @Override
                            protected void initChannel(Channel ch) {
                                ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                                ch.pipeline().addLast(new ObjectEncoder());
                                ch.pipeline().addLast(handler);
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        latch.countDown();
                                    }
                                });
                            }
                        });

                ChannelFuture future = bootstrap.connect("localhost", 9999).sync();
                future.channel().writeAndFlush(request);
                latch.await();
            } catch (Exception e) {
            } finally {
                 group.shutdownGracefully();
            }
            return handler.GetResponse();
        });
        return  (T)o;
    }
}
