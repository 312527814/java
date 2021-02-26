package com.rpc;

import java.io.*;

public class SerDerUtil {
    static ByteArrayOutputStream out = new ByteArrayOutputStream();

    public synchronized static byte[] ser(Object msg) {
        out.reset();
        ObjectOutputStream oout = null;
        byte[] msgBody = null;
        try {
            oout = new ObjectOutputStream(out);
            oout.writeObject(msg);
            msgBody = out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return msgBody;


    }

    public static <T> T deser(Class<T> classInfo, byte[] data) {
        ObjectInputStream oin = null;
        try {
            oin = new ObjectInputStream(new ByteArrayInputStream(data));
            return (T) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oin.close();
            } catch (Exception e) {
            }

        }
        return null;

    }


    public static void main(String[] args) {
        MyHeader myHeader = new MyHeader(1);
        byte[] ser = ser(myHeader);

        MyHeader myHeader2 = new MyHeader(100000);
        byte[] ser1 = ser(myHeader2);

        Long reqid = 132434343l;
        byte[] ser2 = ser(reqid);

        Long reqid2 = 55555l;
        byte[] ser22 = ser(reqid2);

    }
}
