package com.my;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

public class Client2_1 {
    public static void main(String[] args) {
        try {
            String host = "192.168.77.130";
            Socket client = new Socket();
            client.setSoTimeout(1000 * 3);
//            client.bind(new InetSocketAddress(host, 10003));
            client.connect(new InetSocketAddress(host, 9090));

            System.out.println("connected ........");
            Thread.sleep(1000 * 10);

            OutputStream outputStream = client.getOutputStream();

            outputStream.write("dsds".getBytes());

            //outputStream.close();
            System.out.println("client_LocalPort\t" + client.getLocalPort());

            InputStream inputStream = client.getInputStream();
            while (true) {
                byte[] bytes = new byte[1024];
                int read = inputStream.read(bytes);
                System.out.println("client_LocalPort\t" + new String(bytes, 0, read));
                Thread.sleep(1000 * 5);
                outputStream.write((new Date().getTime() + "xieadhia").getBytes());

            }


//            System.in.read();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
