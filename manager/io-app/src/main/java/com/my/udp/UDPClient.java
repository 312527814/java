package com.my.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-09 14:33
 */
public class UDPClient {
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            threads.add(new Thread(() -> {
                try {
                    send("我是客户端");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
    }

    public static void send(String msg) throws IOException {
        // 1.定义服务器的地址、端口号、数据
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 2505;
        byte[] data = msg.getBytes();
        //2.创建数据报，包含发送的数据信息
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //3.创建DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();
        //4.向服务器发送数据报
        for (int i = 0; i < 100; i++) {
            socket.send(packet);
        }


        /*
         * 接收服务器端的数据
         * */
        //1.创建数据报，用于接收服务器端响应的数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        //2.接收服务器响应的数据
        socket.receive(packet2);
        //3.读取数据
        String reply = new String(data2, 0, packet2.getLength());
        System.out.println("服务器说:" + reply);
        //4.关闭资源
        socket.close();
    }
}
