package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class MainServer {
    public static void main(String[] args) {

        WorkServer workServer = new WorkServer();

        MainServer main = new MainServer(workServer);
        main.bind(8089);
        main.start();
    }


    private Selector selector;


    private WorkServer work;
    private int port;

    public MainServer(WorkServer work) {
        this.work = work;
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind(int port) {
        ServerSocketChannel ssc = null;
        try {
            this.port = port;
            if (port > 0)
                this.port = port;
            /* 获取通道 */
            ssc = ServerSocketChannel.open();
            /* 配置非阻塞 */
            ssc.configureBlocking(false);
            /**
             * 配置绑定端口 ServerSocketChannel没有bind()方法，
             * 因此有必要取出对等的ServerSocket并使用它来绑定到一
             * 个端口以开始监听连接
             */
            ssc.socket().bind(new InetSocketAddress("localhost", this.port));
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

    public void start() {
        System.out.println("start......");
        new Thread(this::start0).start();

        new Thread(this::startWork).start();
    }

    private void startWork() {
        this.work.startWork();
    }

    private void start0() {
        try {
            System.out.println("main start.....");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(SelectionKey selectKey) {
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
    public void accept(SelectionKey selectKey) {
        System.out.println("main accept.....");
        ServerSocketChannel ssc = null;
        try {
            ssc = (ServerSocketChannel) selectKey
                    .channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);

            /* 发送信息 */
//            sc.write(ByteBuffer.wrap(new String("Hello World!" + sc.getRemoteAddress())
//                    .getBytes()));
            /* 注册读事件 */

            // sc.register(this.work(), SelectionKey.OP_READ, "政治格局");
            this.work.register(sc);
        } catch (Exception e) {
            if (ssc != null) {
                try {
                    ssc.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 可读事件
     *
     * @param selectKey
     */
    public void read(SelectionKey selectKey) {
        SocketChannel channel = null;
        try {
            // 服务器可读取消息:得到事件发生的Socket通道
            channel = (SocketChannel) selectKey.channel();
            // 创建读取的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(100);
            channel.read(buffer);
            byte[] data = buffer.array();
            String msg = new String(data).trim();

            System.out.println("线程Id:" + Thread.currentThread().getId() + "：客户端：" + msg + ":" + channel.getRemoteAddress());

            String s = channel.getRemoteAddress() + "服务器返回 你好," + msg + "\r\n";
            if (msg.equals("beybey")) {
                s = "beybey";
            }
            ByteBuffer outBuffer = ByteBuffer.wrap(s.getBytes());
            // 将消息回送给客户端
            channel.write(outBuffer);
            if (msg.equals("beybey")) {
                System.out.print(channel.getRemoteAddress() + ":客户端下线");
                channel.close();
            }
//             channel.close();
        } catch (Exception e) {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
