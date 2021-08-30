package com.my._2_lock;


import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {

    @Test
    public void test() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        writeLock.lock();

        System.out.println("write lock start...");

        readLock.lock();


        System.out.println("read lock start...");

        writeLock.unlock();
        System.out.println("write lock unlock...");

        readLock.unlock();
        System.out.println("read lock unlock...");

    }

    @Test
    public void test2() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start...");
            try {
                Thread.sleep(1000 * 500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
            System.out.println("write lock unlock...");
        }).start();
        sleep(1000 * 1);
        new Thread(() -> {
            readLock.lock();
            System.out.println("readLock lock start..." + Thread.currentThread().getName());
            readLock.unlock();
            System.out.println("readLock lock unlock..." + Thread.currentThread().getName());
        }).start();
        sleep(1000 * 2);

        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start..." + Thread.currentThread().getName());
            writeLock.unlock();
            System.out.println("write lock unlock..." + Thread.currentThread().getName());
        }).start();
        sleep(1000 * 2);
        System.in.read();

    }

    @Test
    public void test3() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {
            readLock.lock();
            System.out.println("readLock lock start..." + Thread.currentThread().getName());

            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            readLock.unlock();
        }).start();
        sleep(1000 * 1);
        new Thread(() -> {
            readLock.lock();
            System.out.println("write lock start...");

            sleep(1000 * 5);
            readLock.unlock();
        }).start();
        sleep(1000 * 2);
        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start..." + Thread.currentThread().getName());
            writeLock.unlock();
            System.out.println("write lock unlock..." + Thread.currentThread().getName());
        }).start();

        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start..." + Thread.currentThread().getName());
            writeLock.unlock();
            System.out.println("write lock unlock..." + Thread.currentThread().getName());
        }).start();
        sleep(1000 * 2);
        System.in.read();

    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start...theadname:" + Thread.currentThread().getName());
            readLock.lock();
            writeLock.unlock();
            readLock.unlock();
        }).start();

        System.in.read();
    }

    @Test
    public void test5() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start...theadname:" + Thread.currentThread().getName());
            sleep(1000 * 20000000);
            writeLock.unlock();
        }).start();


        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start...theadname:" + Thread.currentThread().getName());
            sleep(1000 * 20000000);
            writeLock.unlock();
        }).start();

        sleep(1000 * 2);
        new Thread(() -> {
            writeLock.lock();
            System.out.println("write lock start...theadname:" + Thread.currentThread().getName());
            writeLock.unlock();
        }).start();

        System.in.read();
    }

    @Test
    public void test6() {

        int SHARED_SHIFT = 3;
        int SHARED_UNIT = (1 << SHARED_SHIFT);

        int ww = (1 >> SHARED_SHIFT);


        int b = 1 >>> 16;

        int c = 1 << 2;


        int bn = 0;
    }


    @Test
    public void test7() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {
            writeLock.lock();
            System.out.println("readLock lock start..." + Thread.currentThread().getName());
            writeLock.lock();
            try {
                Thread.sleep(1000 * 10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
            writeLock.unlock();
        }).start();
        sleep(1000 * 3);
        new Thread(() -> {
            readLock.lock();
            System.out.println("write lock start...");

            sleep(1000 * 5);
            readLock.unlock();
        }).start();

        System.in.read();

    }

    @Test
    public void test8() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {
            for (int i = 0; i < 65536; i++) {
                try {
                    writeLock.lock();
                    System.out.println("readLock lock start..." + Thread.currentThread().getName());
                } catch (Throwable e) {
                    System.out.println("i  " + i);
                    e.printStackTrace();
                }

            }
            int a = 99;
        }).start();

        System.in.read();

    }

    @Test
    public void test9() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {

            synchronized (this) {
                readLock.lock();
                writeLock.lock();
                System.out.println("writeLock.lock();...");
            }
        }).start();


        System.in.read();

    }

    @Test
    public void test11() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        new Thread(() -> {

            writeLock.lock();

            sleep(1000 * 10);
            writeLock.unlock();
        }).start();

        new Thread(() -> {

            readLock.lock();
            sleep(1000 * 120000);
            System.out.println("readLock 11111111111");
            readLock.unlock();
        }, "t1").start();
        sleep(1000 * 2);
        new Thread(() -> {

            readLock.lock();
            System.out.println("readLock 22222222");
            readLock.unlock();
        }, "t2").start();
        sleep(1000 * 2);
        new Thread(() -> {

            readLock.lock();
            System.out.println("readLock 3333333");
            readLock.unlock();
        }, "t3").start();


        System.in.read();

    }

    @Test
    public void test10() throws IOException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


        new Thread(() -> {

            synchronized (this) {
                try {
                    Thread.sleep(1000 * 10);
                    this.wait();

                    System.out.println("wait.......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        sleep(1000 * 1);
        new Thread(() -> {

            synchronized (this) {
                this.notifyAll();

            }
        }).start();

        System.in.read();

    }

    @Test
    public void test12() {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < 10; i++) {
            blockingQueue.add(i + "");
        }
        Iterator<String> iterator = blockingQueue.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            blockingQueue.remove(next);
            System.out.println(next);
        }

        System.out.println("size:" + blockingQueue.size());
    }
}
