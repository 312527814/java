package com.my;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("开始： " + server);
        try {
            Socket socket = server.accept();
            System.out.println("Connection socket: " + socket);
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Output is automatically flushed by PrintWrite
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                while(true) {
                    String str = in.readLine();
                    if("END".equals(str))
                        break;
                    System.out.println("Echoing: " + str);
                    out.println(str);
                }
            } finally {
                System.out.println("CLosing....");
                socket.close();
            }
        } finally {
            server.close();
        }
    }
}
