package com.rpc;

import com.netty.MyReadHanlder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MyClient {

    private NioEventLoopGroup group = null;
    private NioSocketChannel channel = null;

    public MyClient(InetSocketAddress address) {
        group = new NioEventLoopGroup(1);
        channel = new NioSocketChannel();
        group.register(channel);
        try {
            channel.pipeline()
                    .addLast(new ClientDecode())
                    .addLast(new MyReadHanlder() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            Packmsg packmsg = (Packmsg) msg;
                            MyHeader header = packmsg.getHeader();
                            System.out.println("read Thread.currentThread().getName()=>" + Thread.currentThread().getName() + "  requestid:>" + header.getRequestId());

                            MyResponse<String> response = (MyResponse<String>) packmsg.getBody();
                            CompletableFuture future = RPCUtils.requestMapping.get(header.getRequestId());
                            future.complete(response.getMsg());
                        }
                    })
                    .connect(address).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public   <T> T send(byte[] data) throws InterruptedException, ExecutionException {

        MyHeader myHeader = new MyHeader(data.length);
        CompletableFuture<T> res = new CompletableFuture<>();
        RPCUtils.requestMapping.put(myHeader.getRequestId(), res);
        byte[] headerBytes = SerDerUtil.ser(myHeader);
//        System.out.println("headerBytes=>" + headerBytes.length);
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(headerBytes.length + data.length);
        byteBuf.writeBytes(headerBytes);
        byteBuf.writeBytes(data);
        ChannelFuture send = channel.writeAndFlush(byteBuf);
        System.out.println("send Thread.currentThread().getName()=>" + Thread.currentThread().getName() + "  requestid:>" + myHeader.getRequestId());

        send.sync();

        T t = res.get();
        return t;

    }
}
