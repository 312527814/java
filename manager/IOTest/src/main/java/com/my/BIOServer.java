package com.my;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9090));

        while (true) {
            Socket client = server.accept();
            System.out.println("client\t" + client.getPort() + " connection  " );

            try {
                InputStream is = client.getInputStream();
                byte[] bytes = new byte[1024];
                int read = is.read(bytes);
                //is.close();
                System.out.println("client\t" + client.getPort() + "   " + new String(bytes, 0, read));
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
