package com.my._3_container.queue.blocking;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class _4DelayQueueTest {
    @Test
    public  void  test() throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        Delayed delayed = new Delayed() {
            @Override
            public long getDelay(TimeUnit unit) {
                return 0;
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        };
        delayQueue.add(delayed);

        delayQueue.remove(delayed);
        delayQueue.remove();
        delayQueue.take();
    }

    @Test
    public  void  test2() throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        Delayed delayed = new Delayed() {
            @Override
            public long getDelay(TimeUnit unit) {
                return 0;
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        };
        delayQueue.add(delayed);

        delayQueue.remove(delayed);
        delayQueue.remove();
        delayQueue.take();
        delayQueue.put(delayed);

    }
}
