package com.my._1_aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
//AbstractQueuedLongSynchronizer

    public static void main(String[] args) throws InterruptedException {



        ReentrantLock lock=new ReentrantLock(false);
        Condition condition = lock.newCondition();
        condition.await();
        new Thread(()->{
            lock.lock();
        }).start();

        Thread.sleep(1000);
        new Thread(()->{

            lock.lock();
        }).start();

        ReentrantReadWriteLock lock1=new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock1.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock1.writeLock();
        readLock.lock();
        writeLock.lock();
    }
}
