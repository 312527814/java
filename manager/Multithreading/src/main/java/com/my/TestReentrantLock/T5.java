package com.my.TestReentrantLock;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class T5 {

    private static ReentrantLock lock = new ReentrantLock(true);

    public static void test() {
        lock.lock();

//        try {
//            Thread.sleep(1000 * 1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        lock.unlock();

    }

    public static void test2() {

        lock.lock();
        System.out.print("\r\nThreadName:" + Thread.currentThread().getName());
        lock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(T5::test, "t1");
        thread.start();

        Thread.sleep(10);

        Thread thread2 = new Thread(T5::test2, "t2");
        thread2.start();


        Thread thread3 = new Thread(()->{
            try {
                Thread.sleep(1000*2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread2.interrupt();
        }, "t2");
        thread3.start();


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
