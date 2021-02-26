package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexingSingleThreadServer4 {
    // private static ServerSocketChannel server;
    private static Selector selector;

    private static SocketChannel channel;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("192.168.77.1",9090));
        server.configureBlocking(false);
        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("start........");

        new Thread(() -> {
            boolean fl = true;
            int a = 1;
            while (fl) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (channel != null) {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
                    try {
                        channel.read(buffer);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    buffer.flip();
                    if (buffer.limit() <= 0) {
                        continue;
                    }
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    System.out.println(new String(bytes));

                    buffer.clear();
                    buffer.put("您好啊".getBytes());
                    buffer.flip();
                    try {
                        channel.write(buffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (a++ > 2) {
                        a = 0;
                        selector.wakeup();
                        System.out.println("\r\n.......wakeup\r\n");
                        try {
                            channel.register(selector, SelectionKey.OP_READ, buffer);
                            System.out.println("register............\r\n");
                            channel = null;
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

        while (true) {
            while (true) {

                int select = selector.select();
                System.out.println("select ...........");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isAcceptable()) {
                        System.out.println("begin remove");
                        iterator.remove();
                        System.out.println("after remove");
                        hanlderAccept(next);
                    } else if (next.isReadable()) {
                        hanlderRead(next);
                    } else if (next.isWritable()) {
                        hanlderWriter(next);
                    }
                }

            }
        }
    }


    public static void hanlderAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel client = ssc.accept();
        System.out.println("client:" + client.getRemoteAddress());

        client.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        client.register(selector, SelectionKey.OP_READ, buffer);
    }

    public static void hanlderRead(SelectionKey key) throws IOException {
        System.out.println("begin cancel");
        key.cancel();
        System.out.println("begin cancel");


        SocketChannel client = (SocketChannel) key.channel();

        channel = client;
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        client.read(buffer);

        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes));

        buffer.clear();
        buffer.put("您好啊".getBytes());
        buffer.flip();
        client.write(buffer);



//        try {
//            client.register(selector, SelectionKey.OP_READ, buffer);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

//        ByteBuffer buffer2 = ByteBuffer.allocateDirect(8192);
//        client.register(selector, SelectionKey.OP_READ, buffer2);
    }

    public static void hanlderWriter(SelectionKey key) throws IOException {
        // key.cancel();
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        if (buffer == null) {
            buffer = ByteBuffer.allocateDirect(1024);
        }
        buffer.clear();
        buffer.put((new Date().getTime() + "王海东阿基德").getBytes());
        buffer.flip();
        client.write(buffer);


        client.register(selector, SelectionKey.OP_READ, buffer);
    }
}
