package com.my._2_lock;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestCondition {
    @Test
    public void test() throws IOException {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
//                lock.lock();
                condition.await();

                System.out.println("thread name " + Thread.currentThread().getName());
//                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        sleep(1000 * 10);
        new Thread(() -> {
            lock.lock();
//            sleep(1000 * 200000000);
            condition.signal();

            System.out.println("thread name " + Thread.currentThread().getName());
            lock.unlock();

        }, "t1").start();

        int a = 1;
        System.in.read();
    }

    @Test
    public void test2() throws IOException {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                condition.await();

                System.out.println("thread name " + Thread.currentThread().getName());
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        sleep(1000 * 2);
        new Thread(() -> {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread name " + Thread.currentThread().getName());
            lock.unlock();

        }, "t1").start();

        sleep(1000 * 2);

        int a = 1;
        System.in.read();
    }

    @Test
    public void test3() throws IOException {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                condition.await();

                System.out.println("thread name " + Thread.currentThread().getName());
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        sleep(1000 * 2);
        new Thread(() -> {
            lock.lock();
            condition.signal();

            System.out.println("thread name " + Thread.currentThread().getName());
            lock.unlock();

        }, "t1").start();

        sleep(1000 * 2);

        int a = 1;
        System.in.read();
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
