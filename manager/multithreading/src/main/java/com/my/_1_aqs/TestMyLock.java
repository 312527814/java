package com.my._1_aqs;

import java.util.concurrent.CountDownLatch;

public class TestMyLock {
    private static volatile int a = 0;
    static MyLock lock = new MyLock();

    public static void a() {
        lock.lock();
        String msg = "";
        String name = Thread.currentThread().getName();
        switch (name) {
            case "name 0":
                msg = "0000";break;

            case "name 1":
                msg = "11111111111";break;

            case "name 2":
                msg = "2222222222222222222";break;

            case "name 3":
                msg = "333333333333333333333333333";break;

            case "name 4":
                msg = "444444444444444444444444444444444";break;
        }
        System.out.println("获得锁：" + msg);
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        a++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {


        CountDownLatch latch = new CountDownLatch(5);


        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a();
            }
            latch.countDown();
        });
        thread.setName("name 0");

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                a();
                int dd = 2;
            }
            latch.countDown();
        });
        thread1.setName("name 1");
        Thread thread2 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                a();

                int cc = 0;
            }
            latch.countDown();
        });
        thread2.setName("name 2");

        Thread thread3 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                a();
            }
            latch.countDown();
        });
        thread3.setName("name 3");

        Thread thread4 = new Thread(() -> {

            for (int i = 0; i < 1000; i++) {
                a();
            }
            latch.countDown();
        });

        thread4.setName("name 4");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        latch.await();
        System.out.println(a);


    }
}
