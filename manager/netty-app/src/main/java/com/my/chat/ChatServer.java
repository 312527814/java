package com.my.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServer {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {

                        //向pipeline加入解码器
                        ch.pipeline().addLast("decoder", new StringDecoder());
                        //向pipeline加入编码器
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                            @Override
                            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                                Channel channel = ctx.channel();
                                //将该客户加入聊天的信息推送给其它在线的客户端
                                //该方法会将 channelGroup 中所有的 channel 遍历，并发送消息
                                channelGroup.writeAndFlush("[ 客户端 ]" + channel.remoteAddress()
                                        + " 上线了 " + new java.util.Date() + "\n");
                                //将当前 channel 加入到 channelGroup
                                channelGroup.add(channel);
                                System.out.println(ctx.channel().remoteAddress() + " 上线了" + "\n");

                            }

                            //表示 channel 处于就绪状态, 提示上线
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {

                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                channelGroup.forEach(channel -> {
                                    if (channel != ch) { //不是当前的 channel,转发消息
                                        ch.writeAndFlush("[ 客户端 ]" + channel.remoteAddress() + " 发送了消息：" + msg + "\n");
                                    } else {//回显自己发送的消息给自己
                                        ch.writeAndFlush("[ 自己 ]发送了消息：" + msg + "\n");
                                    }
                                });
                                System.out.println();
                            }
                        });

                    }
                })

                .bind(9999).sync();

        // ChannelFuture future = serverBootstrap.bind(9999).sync();
        channelFuture.channel().closeFuture().sync();
    }
}
