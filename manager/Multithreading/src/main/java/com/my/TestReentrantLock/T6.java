package com.my.TestReentrantLock;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T6 {

    private static ReentrantLock lock = new ReentrantLock(true);
    private static Condition p = lock.newCondition();

    public static void test() {

        lock.lock();
        try {
            p.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        lock.unlock();
    }

    public static void test2() {
        lock.lock();
        int waitQueueLength = lock.getWaitQueueLength(p);
        p.signalAll();
        lock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(T6::test, "t" + i);
            thread.start();
        }


        Thread.sleep(10);

        Thread thread2 = new Thread(T6::test2, "tt");
        thread2.start();


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
