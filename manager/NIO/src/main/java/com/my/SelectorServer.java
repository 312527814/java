package com.my;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class SelectorServer {

    private Selector selector;
    private Selector subSelector;

    private LinkedBlockingDeque<SelectionKey> queue = new LinkedBlockingDeque<>();


    public SelectorServer() {

        try {
            this.selector = Selector.open();
            this.subSelector = Selector.open();

            new Thread(() -> {
                startSubselect();
            }).start();
            while (selector.select() > 0) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSubselect() {
        try {
            System.out.println("startSubselect start...");
            while (this.subSelector.select() > 0) {
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



    public Selector getSelector() {
        return this.selector;
    }

    private void process(SelectionKey selectKey) {
        if (selectKey.isAcceptable()) {
            acceptHandler(selectKey);
        } else if (selectKey.isReadable()) {
            readeHanlder(selectKey);
        }
    }

    private void readeHanlder(SelectionKey selectKey) {
        SocketChannel channel = (SocketChannel) selectKey.channel();
        try {

            // 创建读取的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(100);
            channel.read(buffer);
            byte[] data = buffer.array();
            String msg = new String(data).trim();

            System.out.println("groupName: subselect, 线程Id:" + Thread.currentThread().getId() + "：客户端：" + msg + ":" + channel.getRemoteAddress());

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
        } catch (Exception e) {
            try {
                channel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void acceptHandler(SelectionKey selectKey) {

        ServerSocketChannel ssc = null;
        try {
            ssc = (ServerSocketChannel) selectKey
                    .channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            sc.register(this.subSelector, SelectionKey.OP_READ, "政治格局");
        } catch (ClosedChannelException e) {

        } catch (IOException e) {

        }
    }

}
