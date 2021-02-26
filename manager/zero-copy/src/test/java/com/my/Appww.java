package com.my;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Appww {
    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        String from = "F:\\test\\out.txt";
        RandomAccessFile raf = new RandomAccessFile(from, "rw");
        FileChannel rafchannel = raf.getChannel();
//        System.in.read();
        ByteBuffer buffer = ByteBuffer.allocateDirect(10240000);
        long size = rafchannel.size();
        MappedByteBuffer map = rafchannel.map(FileChannel.MapMode.READ_WRITE, 0, 1000);
        while (true) {
            buffer.clear();
            long position = rafchannel.position();
            raf.seek(position);
            int read = rafchannel.read(buffer);
            if (read <= 0) {
                break;
            }
            byte[] bytes = new byte[read];
            buffer.flip();
            buffer.get(bytes);
            String s = new String(bytes);
            System.out.print(s);
        }

        long end = System.currentTimeMillis();

        System.out.println(" time:" + (end - begin) / 1000);
    }

}
