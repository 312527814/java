package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexingMultiThreadServer5 {
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9090));
        server.configureBlocking(false);
        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("start........");

        while (true) {
            while (true) {
                int select = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if (next.isAcceptable()) {
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
        SocketChannel client = (SocketChannel) key.channel();
        System.out.println("hanlderRead........");
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        client.read(buffer);
        new Thread(() -> {
            buffer.flip();
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            System.out.println(new String(bytes));

            buffer.clear();
            buffer.put("您好啊".getBytes());
            buffer.flip();
            try {
                client.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

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
