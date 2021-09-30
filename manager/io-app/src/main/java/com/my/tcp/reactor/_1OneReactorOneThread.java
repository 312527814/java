package com.my.tcp.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @program:
 * @description: 单reactor单线程
 * @author: liang.liu
 * @create: 2021-09-21 17:59
 */
public class _1OneReactorOneThread {
    public static void main(String[] args) {
        Work work = new Work();
        work.bind(9090);
        work.start();
    }

    static class Work {
        private Selector selector;
        private int port;

        public void bind(int port) {
            this.port = port;
        }

        public void start() {
            try {
                selector = Selector.open();
                ServerSocketChannel server = ServerSocketChannel.open();
                server.bind(new InetSocketAddress(port));
                server.configureBlocking(false);
                server.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    int select = selector.select();
                    if (select > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey next = iterator.next();
                            iterator.remove();
                            if (next.isAcceptable()) {
                                hanlderAccept(next);
                            } else if (next.isReadable()) {
                                clientHanlderRead(next);
                            } else if (next.isWritable()) {
                                hanlderWriter(next);
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void hanlderAccept(SelectionKey key) throws IOException {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = ssc.accept();
            System.out.println("client:" + client.getRemoteAddress());

            client.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(8192);
            client.register(selector, SelectionKey.OP_WRITE, buffer);
        }

        public void clientHanlderRead(SelectionKey key) throws IOException {
            System.out.println("read.........");
//         key.cancel();
            SocketChannel client = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
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

        }

        public void hanlderWriter(SelectionKey key) throws IOException {
            System.out.println("write.........");
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
}
