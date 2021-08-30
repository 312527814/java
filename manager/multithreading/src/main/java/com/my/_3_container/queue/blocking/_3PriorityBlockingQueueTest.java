package com.my._3_container.queue.blocking;

import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

public class _3PriorityBlockingQueueTest {
    @Test
    public  void  test1(){
        PriorityBlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue();
        priorityQueue.add(3);
        priorityQueue.add(5);
        priorityQueue.add(10);
        priorityQueue.add(7);
        priorityQueue.add(9);
        priorityQueue.add(15);
        priorityQueue.add(11);
        priorityQueue.add(13);
        priorityQueue.add(20);
        priorityQueue.add(12);
        priorityQueue.add(4);
        priorityQueue.put(2);

        priorityQueue.poll();
        Integer remove = priorityQueue.remove();
    }
}
