package com.my.TestThreadPool;

import java.util.concurrent.*;

public class TTT1 {
    public static void main(String[] args) {

        int i = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newWorkStealingPool();
        executorService.submit(() -> {
        });
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(4), Executors.defaultThreadFactory());
        executor.execute(() -> {
            System.out.println("dedede");
        });


    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

        }
    }
}
