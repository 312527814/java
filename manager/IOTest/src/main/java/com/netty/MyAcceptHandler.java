package com.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyAcceptHandler extends ChannelInboundHandlerAdapter {

    private NioEventLoopGroup group;

    public MyAcceptHandler(NioEventLoopGroup group) {
        this.group = group;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NioSocketChannel client = (NioSocketChannel) msg;

        System.out.println("read......" + client.remoteAddress());
        client.pipeline().addLast(new MyReadHanlder());
        group.register(client);
    }
}
