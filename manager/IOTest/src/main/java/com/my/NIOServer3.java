package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class NIOServer3 {
    volatile static LinkedList<SocketChannel> clients = new LinkedList<>();

    public static void main(String[] args) throws IOException {


        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false); //重点  OS  NONBLOCKING!!!

        new Thread(() -> {
            while (true) {

                SocketChannel client = null; //不会阻塞？  -1NULL
                try {
                    client = ss.accept();


                    if (client == null) {
                        // System.out.println("null.....");
                    } else {
                        client.configureBlocking(false);
                        int port = client.socket().getPort();
                        //  System.out.println("client...port: " + port);
                        clients.add(client);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


        new Thread(() -> {
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);  //可以在堆里   堆外
            while (true) {
                int size = clients.size();
                if (size > 0) {
                    System.out.println(size + ".......................................");
                }

                for (int i = 0; i < size; i++) {
                    SocketChannel c = clients.get(i);
                    int num = 0;  // >0  -1  0   //不会阻塞
                    try {
                        num = c.read(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (num > 0) {
                        buffer.flip();
                        byte[] aaa = new byte[buffer.limit()];
                        buffer.get(aaa);

                        try {
                            buffer.flip();
                            c.write(buffer);
                        } catch (IOException e) {
                           System.out.println(e.toString());
                        }

                        String b = new String(aaa);
                        System.out.println(c.socket().getPort() + " : " + b);
                        buffer.clear();
                    }
                }
            }

        }).start();
    }
}
