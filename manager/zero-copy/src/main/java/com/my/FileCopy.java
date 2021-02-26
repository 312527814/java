package com.my;

import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        String path = "F:/test/CentOS-7-x86_64-Minimal-1810.iso";
        String path2 = "F:/test/CentOS-7-x86_64-Minimal-1810_2.iso";

        copyFile(path, path2);
        String path3 = "F:/test/CentOS-7-x86_64-Minimal-1810副本.iso";
        String path4 = "F:/test/CentOS-7-x86_64-Minimal-1810副本_2.iso";

//        copyFile2(path3,path4);

        System.in.read();
    }

    public static void copyFile(String from, String to) throws IOException {
        long begin = System.currentTimeMillis();
        FileChannel fromChannel = new RandomAccessFile(from, "rw").getChannel();
        FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel();

        long position = 0;
        long count = fromChannel.size();

        System.out.println("count:" + count);

        fromChannel.transferTo(position, count, toChannel);
//
//        fromChannel.close();
//        toChannel.close();
        long end = System.currentTimeMillis();

        System.out.println("zero-copy time:" + (end - begin) / 1000);
    }



    public static void copyFile2(String from, String to) throws IOException {
        long begin = System.currentTimeMillis();
        FileInputStream input = new FileInputStream(from);
        FileOutputStream output = new FileOutputStream(to);

        byte[] b = new byte[1024];
        int n = 0;
        while ((n = input.read(b)) != -1) {
            output.write(b, 0, n);
        }

        input.close();
        output.close();
        long end = System.currentTimeMillis();

        System.out.println("no-zero-copy time:" + (end - begin) / 1000);
    }
}
