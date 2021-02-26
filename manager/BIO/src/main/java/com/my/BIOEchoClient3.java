package com.my;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BIOEchoClient3 {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

//        for (int i = 0; i < 30000; i++) {
//            Socket client = new Socket("localhost", 8080);
//            InputStream inputStream = client.getInputStream();
//            PrintStream out = new PrintStream(client.getOutputStream());
//            String inputData = "dasdad";
//            out.println(inputData);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            // System.out.print(client.getLocalPort());
//            String s = bufferedReader.readLine();
//            System.out.print(client.getLocalPort() + "/" + ":" + s);
//            System.out.print("\r\n");
//            client.close();
//        }
        AtomicInteger j = new AtomicInteger();
         Lock lock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                Socket client = null;
                try {
                    client = new Socket("localhost", 9999);
                } catch (IOException e) {
                    lock.lock();
                    j.getAndIncrement();
                    //e.printStackTrace();
                    System.out.print(j+"一行j\r\n");
                    lock.unlock();


                }
                InputStream inputStream = null;
                try {
                    inputStream = client.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrintStream out = null;
                try {
                    out = new PrintStream(client.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String inputData = "dasdad";
                out.println(inputData);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                // System.out.print(client.getLocalPort());
                String s = null;
                try {
                    s = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print(client.getLocalPort() + "/" + ":" + s);
                System.out.print("\r\n");
            });
        }
    }
}
