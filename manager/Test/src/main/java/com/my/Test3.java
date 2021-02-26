package com.my;

import java.util.ArrayList;
import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new yeidThead().start();
        }
    }

    static class yeidThead extends Thread {

        @Override
        public void run() {

            System.out.println(1);
            yield();
//            try {
//                sleep(0);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(2);
        }
    }
}


