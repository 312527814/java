package com.my;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App3 {

    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];
    public static void main(String[] args) throws InterruptedException {
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
//            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

        System.out.println((System.currentTimeMillis() - t) + "ms");
 StringBuilder  sb=new StringBuilder(2);
 sb.append('a');


        new CountDownLatch(1).await();



    }
}



