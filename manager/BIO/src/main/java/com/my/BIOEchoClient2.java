package com.my;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class BIOEchoClient2 {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 9999);
        client.setKeepAlive(true);
        OutputStream outputStream = client.getOutputStream();
        PrintStream out = new PrintStream(client.getOutputStream());
        out.println("dsada\r\n");
        out.println("ddd");
        InputStream inputStream = client.getInputStream();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
            String s = null;
            s = bufferedReader.readLine();
            while (!(s = bufferedReader.readLine()).equals("")) {
                System.out.println(s);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static String getString(String prompt) {
        boolean flag = true;    //数据接受标记
        String str = null;
        while (flag) {
            System.out.println(prompt);
            try {
                str = KEYBOARD_INPUT.readLine();    // 读取一行数据
                if (str == null || "".equals(str)) {
                    System.out.println("数据输入错误，不允许为空！");
                } else {
                    flag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
