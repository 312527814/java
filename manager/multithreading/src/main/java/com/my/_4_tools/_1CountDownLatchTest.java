package com.my._4_tools;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class _1CountDownLatchTest {
    @Test
    public void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }).start();
        latch.await();

    }

    @Test
    public void test2() {
        MyCountDownLatch latch = new MyCountDownLatch(2);


        new Thread(() -> {

            for (int i = 0; i < 2; i++) {
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println("after countDown..." + i);
            }


        }).start();


        System.out.println("before await......");
        latch.await();
        System.out.println("after await......");

    }
}

class MyCountDownLatch extends AbstractQueuedSynchronizer {

    public MyCountDownLatch(int count) {
        setState(count);
    }

    public void countDown() {
        for (int i = 0; ; i++) {
            int state = getState();
            if (state < 1) {
                break;
            }
            boolean b = compareAndSetState(state, state - 1);
            if (b) {
                release(1);
                break;
            }
        }

    }

    public void await() {
        acquire(1);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        int state = getState();
        return state == 0;
    }

    @Override
    protected boolean tryRelease(int arg) {
        int state = getState();
        return state == 0;
    }

}
