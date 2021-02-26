package com.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;
import java.util.List;

public class MyNettyServer {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        NioServerSocketChannel server = new NioServerSocketChannel();
        group.register(server);
        server.bind(new InetSocketAddress(9090)).sync();
        server.pipeline().addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                NioSocketChannel client = (NioSocketChannel) msg;
                System.out.println("Accept client=>" + client.remoteAddress());
                client.pipeline()
                        .addLast(new ServerDecode())
                        .addLast(new ChannelInboundHandlerAdapter() {
//                            @Override
//                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//
//                                NioSocketChannel channel = (NioSocketChannel) ctx.channel();
//                                System.out.println("exceptionCaught" + cause);
//                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                Packmsg packmsg = (Packmsg) msg;

                                MyRequest<MyContext> request = (MyRequest<MyContext>) packmsg.getBody();
                                String string = request.getData().getArgs()[0].toString();

                                MyResponse<String> response = new MyResponse<>(string);
                                byte[] responseBytes = SerDerUtil.ser(response);


                                MyHeader header = packmsg.getHeader();
                                header.setDataLength(responseBytes.length);
                                header.setFlag(0x141424);
                                byte[] headerBytes = SerDerUtil.ser(header);
                                ByteBuf byteBuf2 = UnpooledByteBufAllocator.DEFAULT.directBuffer(headerBytes.length + responseBytes.length);
                                byteBuf2.writeBytes(headerBytes);
                                byteBuf2.writeBytes(responseBytes);
                                ctx.channel().writeAndFlush(byteBuf2).sync();
//                                byteBuf2.release();

//                                ReferenceCountUtil.release(byteBuf2);
                            }
                        });

                group.register(client);
            }
        });
        System.out.println("server start...");
        server.closeFuture().sync();
    }
}
