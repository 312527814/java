package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 客户端
 */
public class NIOClient2 {

    public Selector selector;
    private boolean flag = true;

    public NIOClient2(String ip, int port) {
        SocketChannel channel = null;
        try {
            //channel = SocketChannel.open(new InetSocketAddress(ip,port));
            channel = SocketChannel.open();
            // 设置通道为非阻塞
            channel.configureBlocking(false);
            // 获得一个通道管理器
            this.selector = Selector.open();
            // 客户端连接服务器,其实方法执行并没有实现连接
            channel.connect(new InetSocketAddress(ip, port));
            // 注册连接事件。
            channel.register(this.selector, SelectionKey.OP_CONNECT, "我是客户端");
        } catch (ClosedChannelException e1) {
            System.out.println("关闭的通道,无法注册到选择器");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("连接异常!");
            try {
                if (channel != null) channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            e2.printStackTrace();
        }
    }

    /**
     * 轮询选择器
     *
     * @throws IOException
     */
    public void pollSelect() throws Exception {
        /* (阻塞)轮询选择器,直到有事件 */
        while (flag && this.selector.select() > 0) {
            /* 获取事件通知列表 */
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey selectKey = (SelectionKey) ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                process(selectKey);
            }
        }
    }

    /**
     * 处理事件
     *
     * @param selectKey
     */
    public void process(SelectionKey selectKey) throws Exception {
        if (selectKey.isConnectable()) {
            connect(selectKey);
        }
    }

    /**
     * 连接事件
     *
     * @param selectKey
     * @throws Exception
     */
    public void connect(SelectionKey selectKey) throws Exception {
        try {
            SocketChannel channel = (SocketChannel) selectKey
                    .channel();
            /* 如果正在连接,则完成连接 */
            if (channel.isConnectionPending()) {
                /**
                 * connect()方法尚未被调用,调用finishConnect()方法,
                 * 那么将产生NoConnectionPendingException
                 */
                channel.finishConnect();
            }
            /**
             * 在非阻塞模式下调用connect()方法之后,SocketChannel又被切换回了阻塞模式;那么如果
             * 有必要的话，调用线程会阻塞直到连接建立完成,finishConnect()方法接着就会返回true
             * 值。
             */
            /* 设置成非阻塞 */
            channel.configureBlocking(false);

            for (int i = 0; i < 20; i++) {
                String s = "123456789\r\n";
                System.out.println("发送:" + s);
                /* 给服务端发送信息 */
                channel.write(ByteBuffer.wrap(s.getBytes()));
            }
            /* 注册读事件 */
            channel.register(this.selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            throw new IOException("关闭的通道,无法注册到选择器");
        } catch (IOException e) {
            throw new IOException("连接服务或配置失败!");
        }
    }


    public static void main(String[] args) {

        NIOClient2 scw = new NIOClient2("localhost", 8089);
        try {
            scw.pollSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
