package com.my._2_lock;


import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestMyReadWriteLock {

    @Test
    public void test() throws InterruptedException {
        MyReadWriteLock readWriteLock = new MyReadWriteLock();
        MyReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        MyReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        new Thread(() -> {
            writeLock.lock();
            sleep(1000 * 5);
            System.out.println("writeLock unlock.....");
            writeLock.unlock();
        }).start();
        new Thread(() -> {
            readLock.lock();
            System.out.println("readLock lock.....");
            readLock.unlock();
        }).start();

        sleep(1000 * 2);
        new Thread(() -> {
            readLock.lock();
            System.out.println("readLock lock.....222");
            readLock.unlock();
        }).start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        MyReadWriteLock readWriteLock = new MyReadWriteLock();
        MyReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        MyReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        new Thread(() -> {
            readLock.lock();
            sleep(1000 * 10);
            readLock.unlock();

        }).start();

        sleep(1000 * 2);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                writeLock.lock();

                System.out.println("dsdssa111" + finalI);
                writeLock.unlock();
            }).start();
            sleep(100);
            System.out.println(finalI);
        }
        System.in.read();

    }

    @Test
    public void test3() throws Exception {
        MyReadWriteLock readWriteLock = new MyReadWriteLock();
        MyReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        MyReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        new Thread(() -> {
            writeLock.lock();
            readLock.lock();

            sleep(1000 * 10);
            readLock.unlock();

        }).start();


        System.in.read();

    }

    @Test
    public void test4() throws Exception {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        new Thread(() -> {
            writeLock.lock();
            readLock.lock();

            sleep(1000 * 10);
            readLock.unlock();

        }).start();


        System.in.read();

    }











    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
