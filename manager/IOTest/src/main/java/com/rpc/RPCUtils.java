package com.rpc;

import com.netty.MyReadHanlder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import sun.plugin2.jvm.CircularByteBuffer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

public class RPCUtils {
    static List<Integer> list = new ArrayList<>();

    public static ConcurrentHashMap<Long, CompletableFuture> requestMapping = new ConcurrentHashMap<>();


    public static Object send(SocketAddress address, ByteBuf data) throws ExecutionException, InterruptedException {


//        System.out.println("send...");

        NioEventLoopGroup group = new NioEventLoopGroup(2);
        NioSocketChannel socketChannel = new NioSocketChannel();
        group.register(socketChannel);

        socketChannel.pipeline().addLast(new MyReadHandler() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                ByteBuf buf = (ByteBuf) msg;
                byte[] requestIdBytes = new byte[85];
                buf.readBytes(requestIdBytes);
                MyHeader header = SerDerUtil.deser(MyHeader.class, requestIdBytes);
                byte[] dataBytes = new byte[header.getDataLength()];
                buf.readBytes(dataBytes);
                MyResponse<String> response = SerDerUtil.deser(MyResponse.class, dataBytes);
                CompletableFuture future = requestMapping.get(header.getRequestId());
                future.complete(response.getMsg());
                ctx.channel().close();
            }

            @Override
            public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
                requestMapping.forEach(new BiConsumer<Long, CompletableFuture>() {
                    @Override
                    public void accept(Long aLong, CompletableFuture completableFuture) {
                        completableFuture.complete("dsd");
                    }
                });
            }
        }).connect(address).sync();
        MyHeader myHeader = new MyHeader(data.capacity());
        CompletableFuture<String> res = new CompletableFuture<>();
        requestMapping.put(myHeader.getRequestId(), res);
        byte[] headerBytes = SerDerUtil.ser(myHeader);

        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(headerBytes.length + data.capacity());
        byteBuf.writeBytes(headerBytes);
        byteBuf.writeBytes(data);
        ChannelFuture send = socketChannel.writeAndFlush(byteBuf);
        send.sync();
        socketChannel.closeFuture().sync();
        String s = res.get();
        group.shutdownGracefully();
        return s;
    }

}
