package com.my;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            String host = "10.108.2.121";
            String serverHost = "192.168.77.130";
            for (int i = 10000; i < 45000; i++) {
                Socket client = new Socket();
//                client.bind(new InetSocketAddress("localhost", i));
                client.connect(new InetSocketAddress(serverHost, 9090));
                OutputStream outputStream = client.getOutputStream();
                outputStream.write("dsds".getBytes());
                outputStream.close();
                System.out.println("client_LocalPort\t" + client.getLocalPort());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
