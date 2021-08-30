package com.my._3_container.queue.blocking;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class _5SynchronousQueueTest {
    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        new Thread(() -> {
            try {
                System.out.println("begin take  .........a");
                String take = synchronousQueue.take();

                System.out.println("end take ........." + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000 * 10);
        new Thread(() -> {
            try {
                System.out.println("begin add  .........a");
                synchronousQueue.put("a");

                System.out.println("end add .........a");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Test
    public void main() throws InterruptedException, IOException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue(true);
        new Thread(() -> {
            try {
                System.out.println("begin take  .........a");
                String take = synchronousQueue.take();

                System.out.println("end take ........." + take);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t11").start();
        Thread.sleep(1000 * 2);
        new Thread(() -> {
            try {
                System.out.println("begin take  .........a222");
                String take = synchronousQueue.take();

                System.out.println("end take .........222" + take);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t12").start();
        Thread.sleep(1000 * 2);
        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                try {
//                    System.out.println("begin add  .........a");
                    synchronousQueue.put("a");
                    Thread.sleep(1000);
//                    System.out.println("end add .........a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        System.in.read();


    }

    @Test
    public void main2() throws InterruptedException, IOException {

        SynchronousQueue<String> synchronousQueue = new SynchronousQueue(true);
        String take = synchronousQueue.take();

    }
}
