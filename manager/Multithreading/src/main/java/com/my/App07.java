package com.my;

import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Hello world!
 */
public class App07 {

    public static ByteBuffer get() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        for (int i = 0; i < 1024; i++) {
            byteBuffer.put((byte) 9);
        }
        return byteBuffer;
    }

    public static void test() throws IOException {
        System.out.println("test start");
        ByteBuffer byteBuffer = get();


        System.out.println("test");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.in.read();
        test();

        Thread.sleep(1000 * 10);

        System.gc();


        Thread.sleep(1000 * 100000);


    }
}
