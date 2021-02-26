package com.my;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;

public class Test4 {

    public static void main(String[] args) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        String name = ManagementFactory.getRuntimeMXBean().getName();

        System.out.println(name);
// get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            try {
                Thread.sleep(10);
                byteBuffer.put((byte) 1);
            } catch (Exception e) {
                System.out.println(i);
                throw e;
            }

        }

        System.out.println("wabib");
        System.in.read();
    }


}


