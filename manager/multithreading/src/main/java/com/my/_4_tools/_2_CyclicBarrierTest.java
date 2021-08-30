package com.my._4_tools;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class _2_CyclicBarrierTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                //、这是三个人都到齐之后会执行的代码

                System.out.println(Thread.currentThread().getName() + " 三个人都已到达会议室");
                try {
                    Thread.sleep(1000 * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        // 4、模拟每人到会议室所需时间
//                        Thread.sleep(1000 * 5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println("第" + Thread.currentThread().getName() + "个人到达会议室");
                    try {
                        // 5、等待其他人到会议室
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "开始开会");
                }
            }, String.valueOf(finalI)).start();

            Thread.sleep(500);
        }
        System.in.read();
    }

    @Test
    public void testMy() throws IOException, InterruptedException {
        MyCyclicBarrier cyclicBarrier = new MyCyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                //、这是三个人都到齐之后会执行的代码
                System.out.println(Thread.currentThread().getName() + " 三个人都已到达会议室");
                try {
                    Thread.sleep(1000 * 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + Thread.currentThread().getName() + "个人到达会议室");
                    // 5、等待其他人到会议室
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread().getName() + "开始开会");
                }
            }, String.valueOf(finalI)).start();

            Thread.sleep(500);
        }
        System.in.read();
    }
}


class MyCyclicBarrier extends AbstractQueuedSynchronizer {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition;
    private Runnable runnable;
    private int count;
    private int inCount ;

    public MyCyclicBarrier(int count, Runnable runnable) {
        this.count = count;
        inCount=count;
        this.runnable = runnable;
        condition = lock.newCondition();
    }

    public void await() {
        try {
            lock.lock();
            inCount--;
            if (inCount == 0) {
                inCount = count;
                runnable.run();
                condition.signalAll();
            } else {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
