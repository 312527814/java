package com.my;

import java.util.Random;
import java.util.UUID;

/**
 * Hello world!
 */
public class App5 {


    public static void main(String[] args) throws Exception {
        String s = "123";
        App5 app5 = new App5();

        System.out.println(s.hashCode());

        System.out.println("App5  " + app5.name.hashCode());
    }

    public String name = "123";
}
