package com.my;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 */
public class App05 {

    static Object o = null;

    public static void main(String[] args) {

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        o = new Object();
//
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println(".........................................");


        long[] arry=new long[5];
        arry[0]=9;
        arry[1]=2;
        System.out.println(ClassLayout.parseInstance(arry).toPrintable());

    }
}
