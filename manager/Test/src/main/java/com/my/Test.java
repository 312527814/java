package com.my;

import sun.misc.GC;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {
    private static boolean ready = false;
    private static int number = 0;
    private static Object lock = new Object();

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                }
                if (ready) {
                    System.out.println(number);
                    break;
                }

            }
        }
    }

    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) {

        String[] s = new String[2];
        s[0] = "ewe";
        s[1] = "dsds";
        s[1] = null;

    }


}

