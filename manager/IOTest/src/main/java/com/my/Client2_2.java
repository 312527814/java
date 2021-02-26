package com.my;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client2_2 {
    public static void main(String[] args) {
        try {
            String host = "192.168.77.130";
            Socket client = new Socket();
            client.setSoTimeout(1000 * 3);
//            client.bind(new InetSocketAddress(host, 10003));
            client.connect(new InetSocketAddress(host, 9090));

            System.out.println("connected ........");
            Thread.sleep(1000 * 10);

            List<Thread> list = new ArrayList<>();
            OutputStream outputStream = client.getOutputStream();

            for (int i = 0; i < 10; i++) {
                list.add(new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        outputStream.write("abcdefghijklmnopqrstuvwxyz  ".getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
            }
            list.forEach(f -> f.start());


//            System.in.read();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
