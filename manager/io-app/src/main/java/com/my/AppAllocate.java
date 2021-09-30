package com.my;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class AppAllocate
{
    static List<ByteBuffer> list = new ArrayList<>();
    public static void main( String[] args ) throws InterruptedException, IOException {
        for (int i = 0; i < 1000000000; i++) {
            Thread.sleep(1);
            ByteBuffer buf1 = ByteBuffer.allocate(1000);
            for (int j = 0; j < 250; j++) {
                buf1.putInt(j);
            }
            list.add(buf1);
        }
        System.in.read();
    }
}
