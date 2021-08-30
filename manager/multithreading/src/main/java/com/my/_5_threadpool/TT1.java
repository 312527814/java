package com.my._5_threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TT1 {
    public static void main(String[] args) {


        Callable<String> callable = new Callable() {
            @Override
            public String call() throws Exception {

                Thread.sleep(1000);
                return "DDEDEDE";
            }
        };

        FutureTask futureTask2 = new FutureTask(callable);

        Thread thread = new Thread(futureTask2);
        thread.start();


        Thread thread2 = new Thread(() -> {
            try {
                futureTask2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }, "t2");
        thread2.start();

        Thread thread3 = new Thread(() -> {
            try {
                futureTask2.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }, "t3");
        thread3.start();


        try {
            Object o = futureTask2.get();

            String s = "";
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
