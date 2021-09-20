package com.my.http.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class MyRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        if ((msg instanceof DefaultLastHttpContent)) {
            DefaultLastHttpContent msg1 = (DefaultLastHttpContent) msg;
            ByteBuf content = msg1.content();
            int i = content.writerIndex();
            byte[] contents = new byte[i];
            content.readBytes(contents);
            String s = new String(contents);
            System.out.println("DefaultLastHttpContent->" + s + "\r\n");

            int a = 1;
            return;

        }
        if ((msg instanceof DefaultHttpContent)) {
            DefaultHttpContent msg1 = (DefaultHttpContent) msg;
            ByteBuf content = msg1.content();
            int i = content.writerIndex();
            content.resetReaderIndex();
            byte[] contents = new byte[i];
            content.readBytes(contents);
            String s = new String(contents);
            System.out.println(s + "\r\n");

            int a = 1;

        }
        if (!(msg instanceof DefaultHttpRequest)) {
            return;
        }

        String uri = "/a";//req.uri();

        String msg1 = "<html><head><title>test</title></head><body>你请求uri为：" + uri + "</body></html>";
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg1, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, msg1.getBytes().length);

        ctx.writeAndFlush(response);//.addListener(ChannelFutureListener.CLOSE);
    }
}
