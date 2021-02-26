package com.my.TestReentrantLock;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class T8 {

    final static int size = 1000;
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void test() {
        lock.lock();
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        latch.countDown();
        lock.unlock();

    }

    public static synchronized void test3() {
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        latch.countDown();
    }

    static CountDownLatch latch = new CountDownLatch(size);

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(T8::test3));
        }
        for (int i = 0; i < size; i++) {
            threads.get(i).start();
        }

        latch.await();
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    public static void main2(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(T8::test));
        }
        for (int i = 0; i < size; i++) {
            threads.get(i).start();
        }

        latch.await();
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }
}
