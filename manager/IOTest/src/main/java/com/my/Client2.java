package com.my;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

public class Client2 {
    public static void main(String[] args) {
        try {
            String host = "10.108.2.121";
            Socket client = new Socket();
            client.bind(new InetSocketAddress(host, 10000));
            client.connect(new InetSocketAddress(host, 9090));
            OutputStream outputStream = client.getOutputStream();
            outputStream.write("dsds".getBytes());

            //outputStream.close();
            System.out.println("client_LocalPort\t" + client.getLocalPort());

            Thread.sleep(1000 * 5);
            InputStream inputStream = client.getInputStream();
            while (true) {
                byte[] bytes = new byte[1024];
                int read = inputStream.read(bytes);
                System.out.println("client_LocalPort\t" + new String(bytes, 0, read));
                Thread.sleep(1000 * 5);
                outputStream.write((new Date().getTime() + "xieadhia").getBytes());

            }


//            System.in.read();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
