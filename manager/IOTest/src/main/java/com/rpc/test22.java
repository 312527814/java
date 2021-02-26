package com.rpc;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.ByteBuffer;

public class test22 {
    public static void main(String[] args) throws IOException, InterruptedException {

        ByteBuffer byteBuffer2 = ByteBuffer.allocateDirect(1024);
        main2();
        Thread.sleep(20 * 1000);
        System.gc();
        System.out.println("end");
        System.in.read();
    }

    public static void main2() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        byteBuffer.put("dsdsds".getBytes());
        System.out.println("dsdsdsd");

    }
}
