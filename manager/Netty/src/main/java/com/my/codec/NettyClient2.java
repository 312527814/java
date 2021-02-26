package com.my.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.ByteBuffer;

public class NettyClient2 {
    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //pipeline.addLast(new StringEncoder());
                            //pipeline.addLast(new ObjectEncoder());
//                            pipeline.addLast(new LongToByteEncoder());
//                            pipeline.addLast(new ByteToLongDecoder());
                            pipeline.addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


                                    SocketChannel sk = (SocketChannel) ctx.channel();
                                    ByteBuf buf = (ByteBuf) msg;
                                    int i = buf.readerIndex();
                                    int i1 = buf.writerIndex();

                                    byte[] bytes = new byte[i1];

                                    buf.readBytes(bytes);

                                    String string = new String(bytes);
                                    System.out.println("收到服务器消息:" + string);
                                }

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("MyClientHandler发送数据");
                                    //ctx.writeAndFlush("测试String编解码");
                                    //测试对象编解码
                                    //ctx.writeAndFlush(new User(1,"zhuge"));
                                    //测试自定义Long数据编解码器
//                                    ctx.writeAndFlush(1000L);

                                    ByteBuf byteBuf=ctx.alloc().buffer(1024);
                                    for (int i = 0; i < 2; i++) {
                                        byteBuf.writeBytes("点点滴滴".getBytes());
                                    }



                                    ctx.writeAndFlush(byteBuf);


                                }
                            });


                        }
                    });

            System.out.println("netty client start。。");
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
