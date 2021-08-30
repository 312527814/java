package com.my._3_container.queue.blocking;

import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

public class _6LinkedTransferQueueTest {

    @Test
    public void  test() throws InterruptedException {
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue();
//        linkedTransferQueue.put("a");
        linkedTransferQueue.take();
        System.out.println("dsds");
    }
}
