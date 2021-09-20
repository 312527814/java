package com.my.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-13 10:21
 */
public class _1BIOServer2 {
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(9090));
        ServerSocket socket = server.socket();
        while (true) {

            Socket client = socket.accept();
            System.out.println("client\t" + client.getPort() + " connection  ");
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                InputStream is = client.getInputStream();
                byte[] bytes = new byte[1024];

                while (true) {

                    int read = is.read(bytes);

                    System.out.println("read " + read);
                    if (read < 0) {
                        break;
                    }
                    System.out.println("client\t" + client.getPort() + "   " + new String(bytes, 0, read));
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
