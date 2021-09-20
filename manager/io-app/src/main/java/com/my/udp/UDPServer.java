package com.my.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-09 14:32
 */
public class UDPServer {

    public static void main(String[] args) throws IOException {
        //1.创建服务器端DatagtamSocket，指定端口号
        DatagramSocket socket = new DatagramSocket(2505);
        //2.创建数据报，用于接收客服端发送的数据
        byte[] data = new byte[1024];//创建字节数组，指定数据报的大小
        DatagramPacket packet = new DatagramPacket(data, data.length);//
        //3.接收客服端发送的消息
        System.out.println("服务器已经启动。。。。。。。。。");
        while (true) {
            socket.receive(packet);//此方法在收到数据之前会一直阻塞
            //4.读取数据
            String info = new String(data, 0, packet.getLength());
            System.out.println("服务器说" + info);
            /*
             * 响应客服端数据
             * */
            //1.定义客服端的地址、端口号、数据
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            byte[] data2 = "欢迎你".getBytes();
            //2.创建数据报，包含响应的数据信息
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
            //3.响应客服端
            socket.send(packet2);
        }
        //4.关闭资源
//        socket.close();
    }
}
