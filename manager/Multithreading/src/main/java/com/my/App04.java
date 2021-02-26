package com.my;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 */
public class App04 {
    private static Object o;

    public static void test() {
        synchronized (o) {
            try {
                o.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        test();
    }
}
