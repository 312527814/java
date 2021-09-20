package com.my.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-09 14:50
 */
public class _1BIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9090), 2);

        while (true) {

            Socket client = server.accept();
            System.out.println("client\t" + client.getPort() + " connection  ");
            Thread.sleep(1000 * 5);
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

//            new Thread(() -> {
//                try {
//                    InputStream is = client.getInputStream();
//                    byte[] bytes = new byte[1024];
//                    int read = is.read(bytes);
//                    is.close();
//                    System.out.println("client\t" + client.getPort() + "   " + new String(bytes, 0, read));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
        }

    }
}
