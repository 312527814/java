package com.my;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 */
public class App06 {
    private static Object o;

    public static void test() {
        synchronized (o) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        o = new Object();
      System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Thread thread = new Thread(App06::test);
        thread.start();
        Thread thread1 = new Thread(App06::test);
        thread1.start();
        for (; ; ) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thead" + thread1.getState());
        }
    }
}
