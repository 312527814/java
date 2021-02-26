package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class WorkServer {


    private Selector selector;

    public WorkServer() {
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(SocketChannel channel) {
        try {
            this.selector.wakeup();
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            channel.register(this.selector, SelectionKey.OP_READ, "政治格局");

        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    public void startWork() {
        try {
            System.out.println("startWork start.....");
            while (true) {
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


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(SelectionKey selectKey) {

        System.out.println("workServer process ..........");

        if (selectKey.isAcceptable()) { /* 客户端连接事件 */
            //accept(selectKey);
        } else if (selectKey.isReadable()) { /* 可读事件 */
            read(selectKey);
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
