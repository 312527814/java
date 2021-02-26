package com.my;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Appww2 {
    public static void main(String[] args) throws Exception {
        String from = "F:\\test\\out1.txt";
        String to = "F:\\test\\out2.txt";

        long begin = System.currentTimeMillis();
        RandomAccessFile fromrw = new RandomAccessFile(from, "rw");
        FileChannel fromChannel = fromrw.getChannel();
        RandomAccessFile torw = new RandomAccessFile(to, "rw");
        FileChannel toChannel = torw.getChannel();
        long seek = 0;
        while (true) {
            fromrw.seek(seek);
            long l = fromChannel.transferTo(seek, 10, toChannel);
            if (l <= 0) {
                break;
            }
            seek += l;
        }

//        long position = 0;
//        long count = fromChannel.size();
//
//        System.out.println("count:" + count);
//
//        fromChannel.transferTo(position, 10, toChannel);

        fromChannel.close();
        toChannel.close();


        long end = System.currentTimeMillis();

        System.out.println("zero-copy time:" + (end - begin) / 1000);
        while (true) {
        }
    }
}
