package com.my;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 */
public class App05 {
    private static Object o;

    public static void test() {
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        test();
        for (int i = 0; i < 2; i++) {
            new Thread(App05::test).start();
        }
    }
}
