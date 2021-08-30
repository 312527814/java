package com.my._5_threadpool;

import java.util.concurrent.*;

public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {




        ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                r -> {
                    Thread thread = new Thread(r);
//                    thread.setName("处理点击量的线程池");
                    return thread;
                }
        );
//        CompletableFuture<String> voidCompletableFuture = CompletableFuture.completedFuture("dss");

        CompletableFuture<Void> voidCompletableFuture =  CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("runAsync=" + Thread.currentThread().getName() + "|" + Thread.currentThread().isDaemon());
        }, executor);
        voidCompletableFuture.thenRunAsync(()->{
            System.out.println("thenRunAsync=" + Thread.currentThread().getName() + "|" + Thread.currentThread().isDaemon());
        },executor);
//        String s = voidCompletableFuture.get();
//        System.out.println("s=" + s);
        Void aVoid = voidCompletableFuture.get();
        System.out.println("done=" + voidCompletableFuture.isDone());
        TimeUnit.SECONDS.sleep(4);
        System.out.println("done=" + voidCompletableFuture.isDone());

        System.in.read();



    }
    public void test() throws Exception {
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "执行.....");
                    completableFuture.complete("success");//在子线程中完成主线程completableFuture的完成

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();//启动子线程

        String result = completableFuture.get();//主线程阻塞，等待完成
        System.out.println(Thread.currentThread().getName() + "1x:  " + result);


    }

}
