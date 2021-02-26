package com.my;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class Client4 {
    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 10000; i < 10100; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    String host = "10.108.2.121";
                    Socket client = new Socket();
                    client.bind(new InetSocketAddress(host, finalI));
                    client.connect(new InetSocketAddress(host, 9090));
                    OutputStream outputStream = client.getOutputStream();
                    outputStream.write("dsds".getBytes());

                    //outputStream.close();
                    System.out.println("client_LocalPort\t" + client.getLocalPort());

                    InputStream inputStream = client.getInputStream();
                    int c = 1000;
                    while (c > 0) {
                        byte[] bytes = new byte[1024];
                        int read = inputStream.read(bytes);
                        System.out.println("client_LocalPort\t" + new String(bytes, 0, read));
                        //Thread.sleep(1000 * 5);
                        outputStream.write((client.getLocalPort() + ":" + new Date().getTime() + "    " + Thread.currentThread().getName()).getBytes());
                        c--;
                    }
                    countDownLatch.countDown();
                    //            System.in.read();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, i + "th").start();

        }
        countDownLatch.await();
        long end = System.currentTimeMillis();

        System.out.println("花费的时间\t" + (end-start)/1000);
       // System.in.read();

    }
}
