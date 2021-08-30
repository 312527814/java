package com.my._1_aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class MyAQS {
    private int state;
    private Thread ownerThread;
    private BlockingQueue<Thread> waited = new ArrayBlockingQueue<>(10);
    private static Unsafe unsafe;
    private static long stateOffset;

    static {
        try {
            unsafe = getUnsafe();
            stateOffset = unsafe.objectFieldOffset
                    (MyAQS.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void acquire(int acquires) {
        while (true) {
            if (!tryAcquire(acquires)) {
//                System.out.println("befor " + Thread.currentThread().getName());
                waited.add(Thread.currentThread());
                LockSupport.park();

//                System.out.println("after " + Thread.currentThread().getName());
            }else {
                break;
            }
        }
    }

    protected boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            if (compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }


    public void release(int acquires) {
        if (tryRelease(acquires)) {
            Thread t = null;
            while ((t = waited.poll()) != null) {
                LockSupport.unpark(t);
            }
        }

    }

    protected boolean tryRelease(int releases) {
        int c = getState() - releases;
        if (Thread.currentThread() != getExclusiveOwnerThread()) {
            System.out.println("当前：" + Thread.currentThread() + "  ower" + getExclusiveOwnerThread());
            throw new IllegalMonitorStateException();
        }
        boolean free = false;
        if (c == 0) {
            free = true;
            setExclusiveOwnerThread(null);
        }
        setState(c);
        return free;
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean compareAndSetState(int i, int acquires) {
        return unsafe.compareAndSwapInt(this, stateOffset, i, acquires);
    }

    private Thread getExclusiveOwnerThread() {
        return this.ownerThread;
    }

    private void setExclusiveOwnerThread(Thread current) {
        this.ownerThread = current;
    }

    private void setState(int state) {
        this.state = state;
    }

    private int getState() {
        return this.state;
    }
}
