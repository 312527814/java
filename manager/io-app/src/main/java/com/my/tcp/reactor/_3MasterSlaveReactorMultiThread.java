package com.my.tcp.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @program:
 * @description: 主从reactor多线程
 * @author: liang.liu
 * @create: 2021-09-21 17:59
 */
public class _3MasterSlaveReactorMultiThread {
    public static void main(String[] args) {
        EventLoopGroup master = new EventLoopGroup(2, "master");
        EventLoopGroup work = new EventLoopGroup(2, "work");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(master, work);
        bootstrap.bind(9090);
        bootstrap.bind(9099);

        System.out.println("start......");
    }

    static class Bootstrap {
        private EventLoopGroup master;
        private EventLoopGroup work;

        public Bootstrap group(EventLoopGroup master, EventLoopGroup work) {
            this.master = master;
            this.work = work;

            return this;
        }

        public void bind(int port) {
            master.setBootstrap(this);
            work.setBootstrap(this);
            ServerSocketChannel server = null;
            try {
                server = ServerSocketChannel.open();
                server.bind(new InetSocketAddress(port));
                server.configureBlocking(true);
                master.registry(server);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class EventLoopGroup {
        private String name;

        public void setBootstrap(Bootstrap bootstrap) {
            this.bootstrap = bootstrap;
        }

        public Bootstrap getBootstrap() {
            return bootstrap;
        }

        private Bootstrap bootstrap;
        int count = 0;
        private final int threads;
        private EventLoop[] eventLoops;

        public void registry(Channel channel) {
            eventLoops[count++ % eventLoops.length].addChannel(channel);
        }

        public EventLoopGroup(int threads, String name) {
            this.threads = threads;
            this.name = name;
            initSelectorThead();
            startSelectorThead();
        }

        private void initSelectorThead() {
            eventLoops = new EventLoop[threads];
            for (int i = 0; i < threads; i++) {
                EventLoop eventLoop = new EventLoop(this);
                eventLoop.setName(this.name + "-" + i);
                eventLoops[i] = eventLoop;
            }
        }

        private void startSelectorThead() {
            for (int i = 0; i < threads; i++) {
                eventLoops[i].start();
            }
        }

    }

    static class EventLoop extends Thread {
        private final EventLoopGroup group;
        private Selector selector;
        private Executor executor;
        private LinkedBlockingQueue<Channel> queue = new LinkedBlockingQueue<>();
        private int count = 0;

        public EventLoop(EventLoopGroup group) {
            this.group = group;
            try {

                selector = Selector.open();
                executor = new ThreadPoolExecutor(2, 2, 60,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                }, new ThreadPoolExecutor.AbortPolicy());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void addChannel(Channel channel) {
            queue.add(channel);
            selector.wakeup();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int select = selector.select();
                    if (select > 0) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey next = iterator.next();
                            iterator.remove();
                            try {
                                if (next.isAcceptable()) {
                                    hanlderAcceptable(next);
                                }
                                if (next.isReadable()) {
                                    clientHanlderRead(next);
                                }
                            } catch (Exception e) {
                                next.cancel();
                                e.printStackTrace();
                            }

                        }
                    }

                    while (true) {
                        Channel channel = queue.poll();
                        if (channel == null) {
                            break;
                        }

                        if (channel instanceof ServerSocketChannel) {
                            ((ServerSocketChannel) channel).configureBlocking(false);
                            ((ServerSocketChannel) channel).register(this.selector, SelectionKey.OP_ACCEPT);
                        } else if (channel instanceof SocketChannel) {
                            ((SocketChannel) channel).register(this.selector, SelectionKey.OP_READ);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        public void hanlderAcceptable(SelectionKey key) {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel client = null;
            try {
                client = ssc.accept();
                System.out.println(Thread.currentThread().getName() + "线程 accept " + "client:" + client.getRemoteAddress());
                client.configureBlocking(false);
                EventLoop[] eventLoops = this.group.getBootstrap().work.eventLoops;
                eventLoops[count++ % eventLoops.length].addChannel(client);
            } catch (IOException e) {
                key.cancel();
                e.printStackTrace();
            }
        }

        public void clientHanlderRead(SelectionKey key) throws IOException {
            SocketChannel client = (SocketChannel) key.channel();
            System.out.println(Thread.currentThread().getName() + "线程 read " + "client:" + client.getRemoteAddress());
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            client.read(buffer);
            executor.execute(() -> {
                try {
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
                } catch (IOException e) {
                    key.cancel();
                    e.printStackTrace();
                }
            });

        }
    }
}
