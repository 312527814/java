package com.my;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer2 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9090));

        while (true) {
            Socket client = server.accept();
            System.out.println("client\t" + client.getPort() + " connection  " );
            new Thread(() -> {
                try {
                    InputStream is = client.getInputStream();
                    byte[] bytes = new byte[1024];
                    int read = is.read(bytes);
                    System.out.println("client\t" + client.getPort() + "   " + new String(bytes, 0, read));
//                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
