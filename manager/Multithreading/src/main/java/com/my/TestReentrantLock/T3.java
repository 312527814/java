package com.my.TestReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T3 {

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

    public static void test2() {
        lock.lock();

        System.out.print("ThreadName:" + Thread.currentThread().getName());
        lock.unlock();

    }

    public static void testTryTimeout() {
        boolean locked = false;
        try {
            locked = lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(Thread.currentThread().getName());

        if (locked) {
            try {
                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static boolean testTry() {
        boolean locked = false;
        locked = lock.tryLock();


        if (locked) {
            System.out.print("\r\n" + Thread.currentThread().getName());
            try {
                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return locked;


    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(T3::test, "t1").start();
        Thread.sleep(10);

        new Thread(T3::test, "t2").start();
        Thread.sleep(10);
        new Thread(T3::test, "t3").start();
        Thread.sleep(10);
        new Thread(T3::test, "t4").start();
        Thread.sleep(10);
        new Thread(() -> {
            for (; ; ) {
                boolean b = testTry();
                if (b) {
                    break;
                }
            }
        }, "t5").start();

        new Thread(() -> {
            for (; ; ) {
                boolean b = testTry();
                if (b) {
                    break;
                }
            }
        }, "t6").start();

//        for (; ; ) {
//            Thread.sleep(100);
//            System.out.print(lock.getQueueLength());
//        }


    }
}
