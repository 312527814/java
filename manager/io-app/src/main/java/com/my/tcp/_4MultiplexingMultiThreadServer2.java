package com.my.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-13 10:21
 */
public class _4MultiplexingMultiThreadServer2 {
    private static int count = 0;
    private static SelectorThread[] selectorThreads;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9090));
        server.configureBlocking(false);
        selectorThreads = initSeleteorThreads(2);
        System.out.println("start........");
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    if (next.isAcceptable()) {
                        serverHanlderAccept(next);
                    }
                }

            }
        }

    }

    private static void serverHanlderAccept(SelectionKey key) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel client = null;
        try {
            client = ssc.accept();
            System.out.println("client:" + client.getRemoteAddress());
            client.configureBlocking(false);

            SelectorThread selectorThread = selectorThreads[count++ % selectorThreads.length];
            selectorThread.addChanel(client);
        } catch (IOException e) {
            key.cancel();
            e.printStackTrace();
        }


    }

    public static SelectorThread[] initSeleteorThreads(int theads) {
        SelectorThread[] selectorThreads = new SelectorThread[theads];
        for (int i = 0; i < theads; i++) {
            final int j = i;
            selectorThreads[i] = new SelectorThread(j + "号 线程");
        }
        for (int i = 0; i < theads; i++) {
            selectorThreads[i].start();
        }
        return selectorThreads;
    }

    public static class SelectorThread extends Thread {

        private Selector selector;

        public void addChanel(SocketChannel channel) {

            try {
                System.out.println(channel.getRemoteAddress() + "加入了 " + this.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.channels.add(channel);
            selector.wakeup();
        }

        private LinkedBlockingQueue<SocketChannel> channels = new LinkedBlockingQueue<>();

        public SelectorThread(String name) {
            super(name);
            try {
                selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            while (true) {
                try {
                    int select = selector.select();
                    if (select > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        selectionKeys.forEach(selectionKey -> {
                            if (selectionKey.isReadable()) {
                                serverHanlderRead(selectionKey);
                            }
                        });
                        selectionKeys.clear();
                    }

                    while (true) {
                        SocketChannel channel = channels.poll();
                        if (channel == null) {
                            break;
                        }
                        channel.configureBlocking(false);
                        channel.register(this.selector, SelectionKey.OP_READ);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void serverHanlderRead(SelectionKey key) {
            SocketChannel client = (SocketChannel) key.channel();
            try {
                System.out.println("selector " + this.getName() + ": 收到" + client.getRemoteAddress() + " 客户端发来的信息");
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(1000);
            try {
                client.read(buffer);

                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                System.out.println(new String(bytes));

                if (new String(bytes).equals("bye")) {
                    client.close();
                }

//                Scanner sc = new Scanner(System.in);
//                String next = sc.nextLine();
//                buffer.clear();
//                buffer.put(next.getBytes());
//                buffer.flip();
//                client.write(buffer);
            } catch (IOException e) {
                key.cancel();
                e.printStackTrace();
            }
        }
    }
}
