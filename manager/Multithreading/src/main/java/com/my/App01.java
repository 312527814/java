package com.my;

import org.openjdk.jol.info.ClassLayout;


/**
 * Hello world!
 */
public class App01 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Thread thread = new Thread(()->{
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });
        thread.start();
        thread.join();

    }
}
