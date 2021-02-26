package com.my;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 */
public class App06 {
    static Object o = null;

    public static void main(String[] args) {


    }

    public static int calc() {
        int a = 100;
        int b = 200;
        int c = 300;
        App05 aa = new App05();
        int i = aa.hashCode();
        c = (a + b) * c + i;
        return c;
    }
}
