package com.my._1_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private MyAQS aqs = new MyAQS();

    @Override
    public void lock() {
        aqs.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return aqs.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
       if(aqs.tryAcquire(1)) {
           return  true;
       }else {
           Thread.sleep(unit.toNanos(time));
       }
        return false;
    }

    @Override
    public void unlock() {
        aqs.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
