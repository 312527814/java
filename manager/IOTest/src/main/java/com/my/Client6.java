package com.my;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

public class Client6 {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress serverAddr = new InetSocketAddress("192.168.77.130", 9090);
        SocketChannel client = SocketChannel.open();
        client.connect(serverAddr);
        client.configureBlocking(false);

//        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
//        byte[] bytes = "dsdsds点点滴滴".getBytes();
//        System.out.println("bytes:" + bytes.length);

        buffer.put("dsdsddsdsds点点滴滴dsdsds点点滴滴dsdsds点点滴滴dsdsds点点滴滴dsdsds点点滴滴dsdsds点点滴滴dsdsds点点滴滴s点点滴滴".getBytes());
        buffer.flip();
        int read = client.write(buffer);
        System.out.println("end......");
        System.in.read();
    }
}
