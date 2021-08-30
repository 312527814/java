package com.my._3_container.queue.blocking;

import java.util.concurrent.ArrayBlockingQueue;

public class _1ArrayBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(10);
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.take();
    }
}
