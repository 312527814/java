package com.my._5_threadpool;

import java.util.concurrent.FutureTask;

public class TT6 {
    public static void main(String[] args) throws Exception {

        FutureTask<String> futureTask = new FutureTask(() -> {

//            Thread.sleep(1000 * 100);
            return "dsd";
        });


        Thread thread = new Thread(futureTask);
        thread.start();
        String integer = futureTask.get();
        System.out.println("end..........");
        System.in.read();
    }
}
