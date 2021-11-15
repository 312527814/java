package com.my;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App5 {


    public static void main(String[] args) throws Exception {

        byte[] bytes = "hello index+".getBytes();


        new CountDownLatch(1).await();
    }

}
