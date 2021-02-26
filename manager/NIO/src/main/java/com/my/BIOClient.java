package com.my;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BIOClient {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        Socket client = new Socket("localhost", 8089);
        InputStream inputStream = null;
        try {
            inputStream = client.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

       // Thread.sleep(1000 * 3);
        PrintStream out = new PrintStream(client.getOutputStream());
        String inputData = "我是客户端";
        out.println(inputData);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String result = bufferedReader.readLine();
        System.out.print("服务器result:" + result);
        System.out.print("\r\n");

        System.in.read();
    }
}
