package com.my;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * Hello world!
 */
public class App {


    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) throws Exception {
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }

    }

    public static void test(Integer[] DB_DATA) {

        for (int i = 0; i < MAX; i++) {
            //arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

    }


    @Test
    public void testIntern() {
        String s1 = new String("1");
        String s2 = s1;
        s1.intern();
        System.out.println("s1==s2:" +(s1 == s2) );
    }
    @Test
    public void main() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
