package com.my.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-13 10:21
 */
public class _1BIOServer3 {
    private static Selector selector;

    /**
     * 同步accept,异步read
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        selector=Selector.open();
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9090));
        while (true) {
            SocketChannel accept = server.accept();
            System.out.println("client\t" + accept.getRemoteAddress() + " connection  ");

            new Thread(()->{
                try {
                    accept.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(8192);
                    accept.register(selector, SelectionKey.OP_READ,buffer);
                    while (selector.select() > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey next = iterator.next();
                            iterator.remove();
                            if (next.isReadable()) {
                                serverHanlderRead(next);
                            } else {
                                System.out.println(".........");
                            }
                        }

                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }).start();
        }


    }

    private static void serverHanlderRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        client.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes));

        if (new String(bytes).equals("bye")) {
            client.close();
        }

        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();
        buffer.clear();
        buffer.put(next.getBytes());
        buffer.flip();
        client.write(buffer);
        client.register(selector, SelectionKey.OP_WRITE);
    }


}
