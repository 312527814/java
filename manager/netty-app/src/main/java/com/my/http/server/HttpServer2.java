package com.my.http.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.net.InetSocketAddress;

public class HttpServer2 {
    public static void main(String[] args) throws Exception {
        new HttpServer2(8084).start();
    }

    int port;

    public HttpServer2(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                //.handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
//                .childOption(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new HttpServerCodec());// http 编解码
                        pipeline.addLast(new HttpRequestDecoder());// http 编解码
                        pipeline.addLast(new HttpResponseEncoder());
//                        pipeline.addLast("httpAggregator", new HttpObjectAggregator(512 * 1024)); // http 消息聚合器                                                                     512*1024为接收的最大contentlength
//                        pipeline.addLast(new HttpRequestHandler());// 请求处理器
                        pipeline.addLast(new MyRequestHandler());
                    }
                });

        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println(" server start up on port : " + port);
        f.channel().closeFuture().sync();

    }

}