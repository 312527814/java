package com.my.TestReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T2 {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void test() {
        lock.lock();
        try {
            Thread.sleep(1000 * 4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        lock.unlock();

    }

    public static void testTryTimeout() {
        boolean locked = false;
        locked = lock.tryLock();

        System.out.print(Thread.currentThread().getName());
        if (locked) {
            try {
                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    public static void main(String[] args) throws InterruptedException {
        new Thread(T2::test, "t1").start();
        Thread.sleep(10);
        new Thread(T2::test, "t2").start();


    }
}
