package com.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class MyNettyServerModel {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        NioServerSocketChannel server = new NioServerSocketChannel();
        group.register(server);
        server.bind(new InetSocketAddress(9090));

        ChannelPipeline pipeline = server.pipeline();
        pipeline.addLast(new MyAcceptHandler(group));

        System.out.println("start....");
        server.closeFuture().sync();
    }
}
