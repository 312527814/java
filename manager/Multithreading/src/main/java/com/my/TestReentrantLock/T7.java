package com.my.TestReentrantLock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class T7 {
    static MyLock lock = new MyLock();

    static void test() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\r\n");

        try {
            Thread.sleep(1000 * 15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(lock.getWaits() + "dsd");
        lock.unlock();


    }

    public static void main(String[] args) throws Exception {
        new Thread(T7::test, "t1").start();

        Thread.sleep(1000 * 4);

        new Thread(T7::test, "T2").start();
        Thread.sleep(1000 * 1);
        List<Thread> waits = lock.getWaits();
        System.out.println(waits);


        //System.in.read();


    }

    static class MyLock implements Lock {
        public List<Thread> getWaits() {
            return waits;
        }

        private Thread ower = null;
        private List<Thread> waits = new ArrayList<>();
        int state = 0;

        @Override
        public void lock() {
            if (state == 0) {
                state = 1;
                this.ower = Thread.currentThread();
            } else {
                waits.add(Thread.currentThread());
                LockSupport.park();
            }

        }

        @Override
        public void unlock() {
            if (Thread.currentThread() == ower) {
                state = 0;
                this.ower = null;
                this.waits.forEach(f -> {
                    LockSupport.unpark(f);
                });
                this.waits.clear();
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }


        @Override
        public Condition newCondition() {
            return null;
        }
    }
}
