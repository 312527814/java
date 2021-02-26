package com.my;

import java.io.*;
import java.net.Socket;

public class Test6 {

    static int index = 16;

    public static void main(String[] args) throws Exception {

//        Socket client = new Socket("localhost", 20880);
        Socket client = new Socket("localhost", 20880);
        client.setKeepAlive(true);
        OutputStream outputStream = client.getOutputStream();
        DataOutputStream output = new DataOutputStream(outputStream);
        byte sw = -38;
        byte w = -69;
        byte ew = -58;
        int i = sw & 0xff;
        int i2 = w & 0xff;
        int i3 = ew & 0xff;
        byte[] bytes = new byte[273];
        header(bytes);
        version(bytes);
        path(bytes);
        version2(bytes);
        methodName(bytes);
        methodParameterTypesDesc(bytes);
        paravalue(bytes);
        attachment(bytes);

        output.write(bytes);
        output.flush();
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

    public static void header(byte[] bytes) {

        bytes[0] = -38;
        bytes[1] = -69;
        bytes[2] = -58;
        bytes[3] = 0;
        bytes[4] = 0;
        bytes[5] = 0;
        bytes[6] = 0;
        bytes[7] = 0;
        bytes[8] = 0;
        bytes[9] = 0;
        bytes[10] = 0;
        bytes[11] = 0;
        bytes[12] = 0;
        bytes[13] = 0;
        bytes[14] = 1;
        bytes[15] = 1;

    }

    public static void version(byte[] bytes) {
        String s = "\"2.0.2\"";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
        line(bytes);
    }

    public static void path(byte[] bytes) {
        String s = "\"my.com.services.UserService\"";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
        line(bytes);
    }

    public static void version2(byte[] bytes) {
        String s = "\"0.0.0\"";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
        line(bytes);
    }

    public static void methodName(byte[] bytes) {
        String s = "\"get\"";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
        line(bytes);
    }

    public static void methodParameterTypesDesc(byte[] bytes) {
        String s = "\"Lmy/com/services/User;\"";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
        line(bytes);
    }

    public static void paravalue(byte[] bytes) {
        String s = "{\"id\":3,\"name\":\"liu\"}";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
        line(bytes);
    }

    public static void attachment(byte[] bytes) {
        String s = "{\"path\":\"my.com.services.UserService\",\"remote.application\":\"client-app\",\"interface\":\"my.com.services.UserService\",\"version\":\"0.0.0\",\"timeout\":\"10000\"}";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
        line(bytes);
    }


    public static void line(byte[] bytes) {
        String s = "\r\b";
        byte[] bytes1 = s.getBytes();

        for (int i = 0; i < bytes1.length; i++) {
            bytes[index++] = bytes1[i];
        }
    }


}




