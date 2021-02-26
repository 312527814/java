package com.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static void main3(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        for (; ; ) {
            Thread.sleep(1000 * 2);
            NioEventLoopGroup group = new NioEventLoopGroup(1);

            NioSocketChannel socketChannel = new NioSocketChannel();
            group.register(socketChannel);
            socketChannel.pipeline().addLast(new MyReadHandler() {
                @Override
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    System.out.println("channelRead ......" + ctx.channel());
                    Long requestId = 1234l;
                    CompletableFuture future = RPCUtils.requestMapping.get(requestId);
                    RPCUtils.requestMapping.remove(requestId);
                    ctx.channel().close();
                    future.complete("点点滴滴");
                }
            });
            socketChannel.connect(new InetSocketAddress("localhost", 9090));
            Long requestId = 1234l;//Math.abs(UUID.randomUUID().getLeastSignificantBits());

            CompletableFuture<String> res = new CompletableFuture<>();
            RPCUtils.requestMapping.put(requestId, res);

            byte[] requestIdBytes = SerDerUtil.ser(requestId + new Date().getTime());

            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(requestIdBytes.length);
            byteBuf.writeBytes(requestIdBytes);

            ChannelFuture send = socketChannel.writeAndFlush(byteBuf);
            send.sync();
            System.out.println("await  before......" + byteBuf.writerIndex());
            String s = res.get();
            socketChannel.closeFuture().sync();
            System.out.println("await after......");


            System.out.println("over :" + "=>" + s + atomicInteger.get());
        }
    }

    public static void main2(String[] args) throws Exception {
        while (true) {
            NioEventLoopGroup group = new NioEventLoopGroup(1);
            Bootstrap bs = new Bootstrap();
            ChannelFuture connect = bs.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new MyReadHandler() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
                                    System.out.println("channelRead ......" + ctx.channel());
                                    Long requestId = 1234l;
                                    CompletableFuture future = RPCUtils.requestMapping.get(requestId);
                                    future.complete("点点滴滴");
                                    ctx.channel().close();
                                }
                            });
                        }
                    })
                    .connect(new InetSocketAddress("192.168.77.131", 9090));
            Channel client = connect.sync().channel();

            Long requestId = 1234l;//Math.abs(UUID.randomUUID().getLeastSignificantBits());
            CompletableFuture<String> res = new CompletableFuture<>();
            RPCUtils.requestMapping.put(requestId, res);
            byte[] requestIdBytes = SerDerUtil.ser(requestId);
            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(requestIdBytes.length);
            byteBuf.writeBytes(requestIdBytes);

            ChannelFuture send = client.writeAndFlush(byteBuf);
            send.sync();

            System.out.println("await  before......");
            String s = res.get();

            client.closeFuture().sync();

            System.out.println("await after......");
        }
    }

    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        List<Thread> list = new ArrayList<>();
        ICar c = new ProxyFactory().creatProxy(ICar.class);
        CountDownLatch latch = new CountDownLatch(1000);
        for (int i = 0; i < 2000; i++) {
            int finalI = i;
            list.add(new Thread(() -> {
                Object res = c.getr("dsds");
                System.out.println(".............over :" + res);
                latch.countDown();
            }));
        }
        list.forEach(f -> f.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time" + (end - begin) / 1000);
    }

    @Test
    public void clientMode2() {


        ICar c = new ProxyFactory().creatProxy(ICar.class);
        Object res = c.getr("dsds");
        System.out.println("over :" + res);
    }


    @Test
    public void clientNetity() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bs = new Bootstrap();
        ChannelFuture connect = bs.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new MyReadHandler() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
                                System.out.println("channelRead ......" + ctx.channel());
                                Long requestId = 1234l;
                                CompletableFuture future = RPCUtils.requestMapping.get(requestId);
                                future.complete("点点滴滴");
                                ctx.channel().close();
                            }
                        });
                    }
                })
                .connect(new InetSocketAddress("192.168.77.131", 9090));
        Channel client = connect.sync().channel();

        Long requestId = 1234l;//Math.abs(UUID.randomUUID().getLeastSignificantBits());
        CompletableFuture<String> res = new CompletableFuture<>();
        RPCUtils.requestMapping.put(requestId, res);
        byte[] requestIdBytes = SerDerUtil.ser(requestId);
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(requestIdBytes.length);
        byteBuf.writeBytes(requestIdBytes);

        ChannelFuture send = client.writeAndFlush(byteBuf);
        send.sync();

        System.out.println("await  before......");
        String s = res.get();

        client.closeFuture().sync();

        System.out.println("await after......");
    }


}
