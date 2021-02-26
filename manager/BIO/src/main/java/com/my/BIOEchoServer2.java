package com.my;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BIOEchoServer2 {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(9999);
        System.out.println("服务端已经启动，监听端口为：" + 9999);
        boolean flag = true;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (flag) {
            Socket client = socket.accept();
            executorService.submit(new EchoClientHandler(client));
        }
        executorService.shutdown();
        socket.close();


        // new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    private static class EchoClientHandler implements Runnable {

        private Socket client;


        public EchoClientHandler(Socket client) throws SocketException {
            this.client = client;
            client.setKeepAlive(true);
            System.out.println("客户端：" + client.getInetAddress().getHostAddress() + "链接：" + client.getPort());
        }

        public void run() {

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = client.getInputStream();
                outputStream = client.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader bufferedReader = null;

            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gb2312"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            try {
                String s = null;
                boolean flag = false;
                while (!flag) {
                    s = bufferedReader.readLine();
                    flag = s.equals("") || s.equals(" ");
                    System.out.println(s);
                }
                StringBuilder sb = new StringBuilder();
                //拼装http响应的数据格式
                sb.append("http/1.1 200 ok").append("\r\n");
                sb.append("Content-Type:application/json;charset=UTF-8" + "\r\n");
                sb.append("\r\n");//空行
                sb.append("{\"name\":\"dsd\"}");
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                bw.write(sb.toString());
                bw.flush();
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
