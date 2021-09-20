package com.my.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

public class NettyServer2_2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SocketChannel sk = (SocketChannel) ctx.channel();
        System.out.println("收到客户端：" + sk.remoteAddress());

        ByteBuf buf = (ByteBuf) msg;
        int i = buf.readerIndex();
        int i1 = buf.writerIndex();

        byte[] bytes = new byte[i1];

        buf.readBytes(bytes);

        String string = new String(bytes);


        buf.resetReaderIndex();
        ctx.write(buf);
        ctx.writeAndFlush(buf);
        ByteBuf byteBuf = ctx.alloc().buffer(1024);
        byteBuf.writeBytes("收到敷衍我的数据".getBytes());
        ctx.write(byteBuf);
        ctx.flush();
        System.out.println("收到客户端的数据：" + string + "...........");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
