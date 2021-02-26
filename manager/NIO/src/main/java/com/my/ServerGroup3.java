package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerGroup3 {

    public static void main(String[] args) {
        ServerGroup3 serverGroup3 = new ServerGroup3();
        serverGroup3.bind(8089);
        new Thread(() -> {
            serverGroup3.doSelect();
        }).start();

//        new Thread(() -> {
//            try {
//                Thread.sleep(1000*10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(" serverGroup3.selector.wakeup();");
//            serverGroup3.selector.wakeup();
//        }).start();


    }


    private int port = 8080;

    private Selector selector;

    public ServerGroup3() {
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind(int port) {
        ServerSocketChannel ssc = null;
        try {
            if (port > 0)
                this.port = port;
            /* 获取通道 */
            ssc = ServerSocketChannel.open();
            /* 配置非阻塞 */
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress("localhost", this.port));
            /* 将通道注册到选择器 */
            ssc.register(this.selector, SelectionKey.OP_ACCEPT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void doSelect() {
        try {
            //int nu = this.selector.select();
            int select1 = this.selector.select();

            System.out.println("doSelect1........:" + select1);
            this.selector.wakeup();
            Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                SelectionKey selectKey = it.next();
                it.remove();
                process(selectKey);
            }
            int select2 = this.selector.select();
            System.out.println("doSelect2........:" + select2);

            int select3 = this.selector.select();
            System.out.println("select3........:" + select2);
            System.out.println("doSelect   end...");
            System.in.read();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void register(SocketChannel channel) {

    }

    private void process(SelectionKey next) {
        if (next.isAcceptable()) {
            acceptHandler(next);
        } else if (next.isWritable()) {
            writeHandler(next);
        } else if (next.isReadable()) {
            readeHandler(next);
        }
    }

    private void readeHandler(SelectionKey selectKey) {


        SocketChannel channel = (SocketChannel) selectKey.channel();
        try {

            // 创建读取的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(100);
            channel.read(buffer);
            byte[] data = buffer.array();
            String msg = new String(data).trim();


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

    private void writeHandler(SelectionKey next) {
    }

    private void acceptHandler(SelectionKey selectKey) {
        ServerSocketChannel ssc = null;
        try {
            ssc = (ServerSocketChannel) selectKey
                    .channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            this.register(sc);
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


}
