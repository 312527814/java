package com.my;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 */
public class App02 {
    private static Object o;

    public static void test() {
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        test();

        new Thread(() -> {
            test();
        }).start();

    }
}
