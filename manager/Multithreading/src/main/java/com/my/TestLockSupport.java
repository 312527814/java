package com.my;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


public class TestLockSupport {
    protected static Object o = new Object();

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 1) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        // LockSupport.unpark(t);

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 senconds!");
        Object blocker = LockSupport.getBlocker(t);
       // LockSupport.unpark(t);

    }
}