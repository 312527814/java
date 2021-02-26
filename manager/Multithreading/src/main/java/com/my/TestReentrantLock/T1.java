package com.my.TestReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T1 {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void test() {
        lock.lock();
        try {
            Thread.sleep(1000 * 400000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        lock.unlock();

    }

    public static void test2() {
        lock.lock();

        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        lock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(T1::test, "t1").start();
        Thread.sleep(10);
        new Thread(T1::test2, "t2").start();
    }
}
