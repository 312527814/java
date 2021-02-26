package com.my.TestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class T_DelayedWorkQueue {
    private static ReentrantLock lock = new ReentrantLock(true);

    private static void test() {

        try {
            lock.lockInterruptibly();
//            while (true) {
//                if (lock == null) {
//                    break;
//                }
//
//            }

            lock.unlock();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("dededede");

    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue queue=new ArrayBlockingQueue(2);
//        queue.put();

        Thread t1 = new Thread(T_DelayedWorkQueue::test);
        t1.start();
        Thread.sleep(1000 * 1);
        Thread t2 = new Thread(T_DelayedWorkQueue::test);
        t2.start();
        Thread.sleep(1000 * 3);

        t2.interrupt();

    }
}
