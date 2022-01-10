package com.my;

import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.TimerTask;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {


    public static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) throws Exception {

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null, null);
        Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();
        objectObjectHashtable.put(null, null);
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

    @Test
    public void tt() throws InterruptedException {
        final  HashedWheelTimer timer = new HashedWheelTimer(
                Executors.defaultThreadFactory(),100, TimeUnit.MILLISECONDS,32);
        timer.newTimeout(new TimerTask() {
            @Override
            public void run(final Timeout timeout) throws Exception {
                System.out.println("lee");
                System.out.printf(".....｜｜" + timeout);

            }
        }, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
        new CountDownLatch(1).await();

        //final HashedWheelTimer timer = new HashedWheelTimer(
        //        Executors.defaultThreadFactory(), 100, TimeUnit.MILLISECONDS, 32);
        //
        //timer.newTimeout(new TimerTask() {
        //    @Override
        //    public void run(final Timeout timeout) throws Exception {
        //        System.out.println("lee");   //打印名字
        //    }
        //}, 1000, TimeUnit.MILLISECONDS);
        //
        //Thread.sleep(10000);
        //new CountDownLatch(1).await();
    }

    @Test
    public void testTimerOverflowWheelLength() throws InterruptedException {
        final HashedWheelTimer timer = new HashedWheelTimer(
                Executors.defaultThreadFactory(), 100, TimeUnit.MILLISECONDS, 32);

        timer.newTimeout(new TimerTask() {
            @Override
            public void run(final Timeout timeout) throws Exception {
                System.out.println("lee");   //打印名字
            }
        }, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
        new CountDownLatch(1).await();
    }


}
