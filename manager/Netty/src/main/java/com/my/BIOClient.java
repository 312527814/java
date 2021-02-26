package com.my;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.io.*;
import java.net.Socket;

public class BIOClient {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {

        Socket client = new Socket("localhost", 8000);
        InputStream inputStream = null;
        try {
            inputStream = client.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintStream out = new PrintStream(client.getOutputStream());
        String inputData = "我是客户端";
        out.println(inputData);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String result = bufferedReader.readLine();
        System.out.print("服务器result:" + result);
        System.out.print("\r\n");
    }
}
