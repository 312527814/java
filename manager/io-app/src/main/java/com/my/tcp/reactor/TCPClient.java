package com.my.tcp.reactor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-09 14:52
 */
public class TCPClient {
    public static void main(String[] args) {
        try {
            String serverHost = "127.0.0.1";
            Socket client = new Socket();
//            client.bind(new InetSocketAddress("localhost", 10001));
            client.connect(new InetSocketAddress(serverHost, 9090));
            OutputStream outputStream = client.getOutputStream();
            for (int j = 0; j < 1; j++) {
                outputStream.write("我是客户端".getBytes());
            }
            System.in.read();
            // outputStream.close();
            System.out.println("client_LocalPort\t" + client.getLocalPort());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
