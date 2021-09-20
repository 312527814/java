package com.my;

import java.util.Random;
import java.util.UUID;

/**
 * Hello world!
 */
public class App5 {


    public static void main(String[] args) throws Exception {
//        int a = 1;
//        int b = 2;
//        for (int i = 0; i < 134; i++) {
//            int c = 32;
//        }

        String string = UUID.randomUUID().toString().replace("-", "");
        System.out.println(string);

    }
}
