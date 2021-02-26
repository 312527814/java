package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端
 */
public class SocketServer {

    /**
     * 服务器默认绑定端口
     */
    public static final int DEFAULT_PORT = 8080;

    /**
     * 选择器
     */
    public Selector selector;

    public SocketServer(String ip, int port) {
        ServerSocketChannel ssc = null;
        try {
            int _port = DEFAULT_PORT;
            if (port > 0)
                _port = port;
            /* 获取通道 */
            ssc = ServerSocketChannel.open();
            /* 配置非阻塞 */
            ssc.configureBlocking(false);

            /**
             * 配置绑定端口 ServerSocketChannel没有bind()方法，
             * 因此有必要取出对等的ServerSocket并使用它来绑定到一
             * 个端口以开始监听连接
             */
            ssc.socket().bind(new InetSocketAddress(ip, _port));
            /* 获取选择器 */
            this.selector = Selector.open();
            /* 将通道注册到选择器 */
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);

            System.out.println("启动成功！");
        } catch (ClosedChannelException e1) {
            System.out.println("关闭的通道,无法注册到选择器");
            e1.printStackTrace();
        } catch (IOException e2) {
            try {
                if (ssc != null) ssc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("服务器绑定端口冲突");
            e2.printStackTrace();
        }
    }

    /**
     * 轮询选择器
     *
     * @throws Exception
     */
    public void pollSelect() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.submit(() -> {
//            /* (阻塞)轮询选择器,直到有事件 */
//            while (true) {
//                System.out.print("1前线程Id:" + Thread.currentThread().getId() + "\r\n");
//                try {
//                    if (!(selector.select() > 0)) break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.print("1后线程Id:" + Thread.currentThread().getId() + "\r\n");
//
//                /* 获取事件通知列表 */
//                Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
//
//                while (it.hasNext()) {
//                    SelectionKey selectKey = it.next();
//                    it.remove();
//                    try {
//                        process(selectKey);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        continue;
//                    }
//                }
//            }
//        });

//        executorService.submit(() -> {
//            /* (阻塞)轮询选择器,直到有事件 */
//            while (true) {
//                System.out.print("2前线程Id:" + Thread.currentThread().getId() + "\r\n");
//                try {
//                    if (!(selector.select() > 0)) break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.print("2后线程Id:" + Thread.currentThread().getId() + "\r\n");
//                /* 获取事件通知列表 */
//                Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
//                while (it.hasNext()) {
//                    SelectionKey selectKey = it.next();
//                    it.remove();
//                    try {
//                        process(selectKey);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        continue;
//                    }
//                }
//            }
//        });


        /* (阻塞)轮询选择器,直到有事件 */
        while (this.selector.select() > 0) {
            /* 获取事件通知列表 */
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey selectKey = it.next();
                it.remove();
                try {
                    process(selectKey);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    /**
     * 事件处理
     *
     * @param selectKey
     */
    public void process(SelectionKey selectKey) throws Exception {
        if (selectKey.isAcceptable()) { /* 客户端连接事件 */
            accept(selectKey);
        } else if (selectKey.isReadable()) { /* 可读事件 */
            read(selectKey);
        }
    }

    /**
     * 连接事件
     *
     * @param selectKey
     */
    public void accept(SelectionKey selectKey) throws Exception {
        ServerSocketChannel ssc = null;
        try {
            ssc = (ServerSocketChannel) selectKey
                    .channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);

            /* 发送信息 */
            sc.write(ByteBuffer.wrap(new String("Hello World!")
                    .getBytes()));
            /* 注册读事件 */
            sc.register(this.selector, SelectionKey.OP_READ, "政治格局");
        } catch (ClosedChannelException e) {
            if (ssc != null)
                ssc.close();
            throw new IOException("关闭的通道,无法注册到选择器");
        } catch (IOException e) {
            if (ssc != null)
                ssc.close();
            throw new IOException("连接服务或配置失败!");
        }
    }

    /**
     * 可读事件
     *
     * @param selectKey
     */
    public void read(SelectionKey selectKey) throws Exception {
        SocketChannel channel = null;

        try {
            // 服务器可读取消息:得到事件发生的Socket通道
            channel = (SocketChannel) selectKey.channel();
            // 创建读取的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(100);
            channel.read(buffer);
            byte[] data = buffer.array();
            String msg = new String(data).trim();
            System.out.println("线程Id:" + Thread.currentThread().getId() + "：客户端：" + msg+":"+channel.getRemoteAddress());

            String s = channel.getRemoteAddress() + "服务器返回 你好\r\n";

            Thread.sleep(1000);
            ByteBuffer outBuffer = ByteBuffer.wrap(s.getBytes());
            // 将消息回送给客户端
            channel.write(outBuffer);

//             channel.close();
        } catch (Exception e) {
            if (channel != null)
                channel.close();
            throw new Exception("客户端将通道关闭,无法从通道读入缓冲或将缓冲数据写回通道!");
        }
    }


    public static void main(String[] args) {
        App app = new App();
        App app2 = new App();
        App app3 = new App();
        App app4 = new App();
        App app5 = new App();



        try {
            SocketServer ss = new SocketServer("localhost", 8080);

            ss.pollSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}