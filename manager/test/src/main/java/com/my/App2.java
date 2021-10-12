package com.my;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App2 {
    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) throws Exception {
        String string = new Date().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS",Locale.CHINA);
        Date now = new Date();
        String format = dateFormat.format(now);

        new CountDownLatch(1).await();
//        System.gc();
    }
}
