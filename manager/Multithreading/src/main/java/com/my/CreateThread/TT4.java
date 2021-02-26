package com.my.CreateThread;

public class TT4 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println("dsdsdsa");

        });
        thread.start();
        Thread.yield();
        thread.join();
    }
}
