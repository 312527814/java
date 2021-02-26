package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class MyNettyClientModel {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        NioSocketChannel socketChannel = new NioSocketChannel();
        group.register(socketChannel);


        socketChannel.pipeline().addLast(new h());
        socketChannel.connect(new InetSocketAddress("192.168.77.130", 9090));

        socketChannel.closeFuture().sync();

        System.out.println("end.......");
    }

    static class h extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
//        CharSequence str = buf.readCharSequence(buf.readableBytes(), CharsetUtil.UTF_8);
            CharSequence str = buf.getCharSequence(0, buf.readableBytes(), CharsetUtil.UTF_8);
            System.out.println(str);
            ctx.writeAndFlush(buf);
            ctx.channel().close();
        }
    }
}
