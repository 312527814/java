package com.my;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {


        final Object flag = new Object();
        new Thread(() -> {
            synchronized (flag) {
                System.out.println("1 获得锁...");
                try {
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("before flag.wait()");
                    flag.wait();
                    System.out.println("after flag.wait()");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (flag) {
                System.out.println("2 获得锁...");
                try {
                    flag.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
