package com.my._3_container.queue;

import org.junit.Test;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {

        PriorityQueue<String> priorityQueue = new PriorityQueue();
        priorityQueue.add("a");
        priorityQueue.add("c");
        priorityQueue.add("b");
        priorityQueue.add("e");
        priorityQueue.add("d");

        priorityQueue.add("f");
    }

    @Test
    public void test1() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        priorityQueue.add(3);
        priorityQueue.add(5);
        priorityQueue.add(2);
        priorityQueue.add(9);
        priorityQueue.add(7);
        priorityQueue.add(15);
        priorityQueue.add(11);
        priorityQueue.add(13);
        priorityQueue.add(20);
        priorityQueue.add(4);

    }

    @Test
    public void test2() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(6);
        priorityQueue.add(9);
        priorityQueue.add(5);
        priorityQueue.add(15);
        priorityQueue.add(11);
        priorityQueue.add(13);
        priorityQueue.add(20);
        priorityQueue.add(7);

        priorityQueue.remove(7);
        Integer poll = priorityQueue.poll();
        Iterator<Integer> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }


    }


}
