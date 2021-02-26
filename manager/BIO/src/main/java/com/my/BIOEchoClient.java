package com.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class BIOEchoClient {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 9999);
        Scanner scan = new Scanner(client.getInputStream());
        scan.useDelimiter("\n");
        PrintStream out = new PrintStream(client.getOutputStream());
        boolean flag = true;
        while (flag) {
            String inputData = getString("请输入要发送的内容：").trim();
            out.println(inputData);
            if (scan.hasNext()) {
                String str = scan.next();
                System.out.println(str);
            }
            if ("byebye".equalsIgnoreCase(inputData)) {
                flag = false;
            }
        }
        client.close();

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
