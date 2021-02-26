package com.my;

import sun.security.krb5.internal.TGSRep;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexingSingleThreadServer3 {
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open();


        client.configureBlocking(false);
        selector = Selector.open();
        System.out.println("start........");
        SelectionKey key = client.register(selector, SelectionKey.OP_CONNECT);
        client.connect(new InetSocketAddress("192.168.77.130", 12345));

        while (true) {
            System.out.println("dede");
            while (selector.select() > 0) {
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
                    } else if (next.isConnectable()) {
                        hanlderConnectable2(next);
                    }
                }

            }
        }
    }

    public static void hanlderConnectable2(SelectionKey key) throws IOException {
        SocketChannel sc = ((SocketChannel) key.channel());
        sc.finishConnect();
        //key.interestOps(SelectionKey.OP_WRITE);

        sc.register(selector, SelectionKey.OP_WRITE);
    }

    public static void hanlderConnectable(SelectionKey key) throws IOException {
        System.out.println("hanlderConnectable");
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        if (buffer == null) {
            buffer = ByteBuffer.allocateDirect(1024);
        }
        buffer.clear();
        buffer.put((new Date().getTime() + "王海东阿基德").getBytes());
        buffer.flip();

        boolean b = client.finishConnect();

        client.write(buffer);
        client.register(selector, SelectionKey.OP_READ, buffer);
    }

    public static void hanlderAccept(SelectionKey key) throws IOException {
        System.out.println("hanlderAccept");
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel client = ssc.accept();
        System.out.println("client:" + client.getRemoteAddress());

        client.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        client.register(selector, SelectionKey.OP_READ, buffer);
    }

    public static void hanlderRead(SelectionKey key) throws IOException {

        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        client.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println("read=>" + new String(bytes));
        if(bytes.length==0){
            client.close();
        }
//        client.close();
        // client.register(selector, SelectionKey.OP_WRITE, buffer);

    }

    public static void hanlderWriter(SelectionKey key) throws IOException {
        // key.cancel();
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        if (buffer == null) {
            buffer = ByteBuffer.allocateDirect(1024);
        }
        buffer.clear();
        String s = new Date().getTime() + "王海东阿基德";
        buffer.put(s.getBytes());
        buffer.flip();
        client.write(buffer);
        System.out.println("writer=>" + new String(s));
        client.register(selector, SelectionKey.OP_READ, buffer);
    }
}
