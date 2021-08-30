package com.my._2_lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class MyRWAQS {
    public static class Node {
        private Thread thread;
        private NodeStatus status;

        public boolean isShare() {
            return status == NodeStatus.share;
        }

        public Node(Thread thread, NodeStatus status) {
            this.thread = thread;
            this.status = status;
        }

        public enum NodeStatus {
            share, exclusive;
        }

    }

    private final int SHARED_UNIT = 65536;
    private final int MAX_COUNT = 65535;//设置读锁和写锁的最大数量
    private volatile int state = 0;// state % SHARED_UNIT 为写锁的重入次数，state/SHARED_UNIT 为共享锁的次数
    private volatile Thread owner;
    private volatile BlockingQueue<Node> waited = new LinkedBlockingQueue();

    private static Unsafe unsafe;
    private static long stateOffset;

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

    static {
        try {
            unsafe = getUnsafe();
            stateOffset = unsafe.objectFieldOffset
                    (MyRWAQS.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private boolean tryAcquire(int arg) {
        //todo
        Thread current = Thread.currentThread();
        int s = state;
        int exlusiveCount = getExlusiveCount();
        if (s != 0) {
            //s!=0 and exlusiveCount=0 说明当前是读锁。
            if (exlusiveCount == 0) {
                return false;
            }
            if (current != owner) {//exlusiveCount>0 说明有写锁，如果当前的线程不是锁持有者 要返回false
                return false;
            }
            state = state + arg;
            return true;
        }
        boolean b = compareAndSetState(s, state + arg);
        if (b) {
            setExclusiveOwnerThread(current);
        }
        return b;
    }

    public void acquire(int arg) {
        while (true) {
            if (!tryAcquire(arg)) {
                //todo
                waited.add(new Node(Thread.currentThread(), Node.NodeStatus.exclusive));
                LockSupport.park(Thread.currentThread());
            } else {
                return;
            }

        }

    }

    private boolean tryRelease(int arg) {

        if (owner != Thread.currentThread() || getExlusiveCount() < 1) {
            throw new Error("释放失败");
        }
        int s = state;

        boolean b = compareAndSetState(s, s - 1);
        if (b && state == 0) {
            setExclusiveOwnerThread(null);
        }
        return b;
    }

    public void release(int arg) {
        if (tryRelease(arg)) {
            //todo
            // 找到第一个入队的线程
            // 释放线程
            if (waited.size() > 0) {
                Node node = waited.remove();
                // 唤醒所有读锁的线程
                if (node.isShare()) {
                    Iterator<Node> iterator = waited.iterator();
                    while (iterator.hasNext()) {
                        Node next = iterator.next();
                        if (next.isShare()) {
                            waited.remove(next);
                            LockSupport.unpark(next.thread);
                        }

                    }
                }

                LockSupport.unpark(node.thread);
            }
        }
    }


    private boolean tryShareAcquire(int arg) {
        int s = state;
        if (getExlusiveCount() > 0 && Thread.currentThread() != owner) {
            return false;
        }
        boolean b = unsafe.compareAndSwapInt(this, stateOffset, s, state + arg * SHARED_UNIT);
        return b;
    }


    public void shareAcquire(int arg) {
        while (true) {
            if (!tryShareAcquire(arg)) {
                //todo
                // 加入等待队列
                // park 线程
                waited.add(new Node(Thread.currentThread(), Node.NodeStatus.share));
                LockSupport.park(Thread.currentThread());
            } else {
                return;
            }

        }
    }

    private boolean tryShareRelease(int arg) {
        int s = state;
        boolean b = compareAndSetState(s, s - arg * SHARED_UNIT);
        return b;
    }

    public void shareRelease(int arg) {
        if (tryShareRelease(arg)) {
            //todo
            // 找到第一个入队的线程
            // 释放线程
            if (waited.size() > 0) {
                Node node = waited.remove();
                LockSupport.unpark(node.thread);
            }
//            Iterator<Node> iterator = waited.iterator();
//            while (iterator.hasNext()){
//                Node next = iterator.next();
//                waited.remove(next);
//                LockSupport.unpark(next.thread);
//            }
        }
    }


    private int getShareCount() {
        // 如果是SHARED_UNIT的整数倍说明就是share状态
        return state / SHARED_UNIT;
    }

    private int getExlusiveCount() {
        return state % SHARED_UNIT;
    }

    private final boolean compareAndSetState(int expect, int update) {

        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private final void setExclusiveOwnerThread(Thread thread) {
        owner = thread;
    }
}
