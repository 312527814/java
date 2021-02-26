package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class NIOServer4 {
    static volatile LinkedList<SocketChannel> clients = new LinkedList<>();
    public static void main(String[] args) throws IOException {




        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(9090));
        ss.configureBlocking(false); //重点  OS  NONBLOCKING!!!

        new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                //System.out.println(size);
                if(size>0){
                    System.out.println("System.out.println(size);");
                }

            }

        }).start();
    }
}
