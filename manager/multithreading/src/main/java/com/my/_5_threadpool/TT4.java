package com.my._5_threadpool;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TT4 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("dsdsdsa");

        });
        thread.start();
        Thread.yield();
        thread.join();

    }

    @Test
    public void ss() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();


            }

            int a = 0;
            System.out.println(a);
        });
        thread.start();
        Thread.sleep(2000);
        Thread thread2 = new Thread(() -> {
            thread.interrupt();
        });
        thread2.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sss() throws IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Runtime.getRuntime().availableProcessors() * 2, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1)
                , new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        executor.execute(() -> {
            System.out.println("........1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            System.out.println("........2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            System.out.println("........3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int activeCount = executor.getActiveCount();
        System.out.println(activeCount);
        System.in.read();
    }
}
