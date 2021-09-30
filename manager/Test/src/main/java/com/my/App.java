package com.my;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

/**
 * Hello world!
 */
public class App {


    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) throws Exception {

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null,null);
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        objectObjectHashtable.put(null,null);
        String ddd = new String("ddd");
        if (ddd == "ddd") {
            System.out.println(true);
        }

        //41位二进制最小值
        String minTimeStampStr = "00000000000000000000000000000000000000000";
        //41位二进制最大值
        String maxTimeStampStr = "11111111111111111111111111111111111111111";
        //转10进制
        long minTimeStamp = new BigInteger(minTimeStampStr, 2).longValue();
        long maxTimeStamp = new BigInteger(maxTimeStampStr, 2).longValue();

        System.out.println(maxTimeStamp);
        //一年总共多少毫秒
        long oneYearMills = 1L * 1000 * 60 * 60 * 24 * 365;
        //算出最大可以多少年
        System.out.println((maxTimeStamp - minTimeStamp) / oneYearMills);

    }

    public static void test(Integer[] DB_DATA) {

        for (int i = 0; i < MAX; i++) {
            //arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

    }


}
