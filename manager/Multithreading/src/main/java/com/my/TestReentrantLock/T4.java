package com.my.TestReentrantLock;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class T4 {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void test() {
        lock.lock();

        try {
            Thread.sleep(1000 * 400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        lock.unlock();

    }

    public static void test2() {
        try {
            lock.lockInterruptibly();
            System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(T4::test, "t1");
        thread.start();

        Thread.sleep(10);

        Thread thread2 = new Thread(T4::test2, "t2");
        thread2.start();

        Thread.sleep(1000);
        thread2.interrupt();
    }
}
