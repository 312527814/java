package com.my.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HttpClient {
    public static void main(String[] args) {
        try {
            String serverHost = "127.0.0.1";
            Socket client = new Socket();
            client.connect(new InetSocketAddress(serverHost, 8099));
            OutputStream outputStream = client.getOutputStream();
            String msg="GET /async/index HTTP/1.1\n" +
                    "Host: 127.0.0.1\n" +
                    "Content-Type: application/x-www-form-urlencoded\n" +
                    "Content-Length: 113\n";
            msg += "\n\n";
            outputStream.write(msg.getBytes());
            byte[] bytes = new byte[1000];
            while (true) {
                int read = client.getInputStream().read(bytes);
                if(read<0){
                    break;
                }
                String msgR = new String(bytes, 0, read);
                System.out.println("response: " +msgR +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
