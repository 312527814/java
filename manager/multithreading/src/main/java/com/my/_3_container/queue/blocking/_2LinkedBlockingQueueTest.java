package com.my._3_container.queue.blocking;

import java.util.concurrent.LinkedBlockingQueue;

public class _2LinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("a");
        queue.take();
    }
}
