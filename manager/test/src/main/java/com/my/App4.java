
package com.my;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App4 {
    static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
//        byte[] array = new byte[100 * 1024 * 1024];
//        byte[] array2 = new byte[100 * 1024 * 1024];

        byte[] array3 = new byte[700 * 1024 * 1024];

        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {

            System.out.println(memoryPoolMXBean.getName() + "   total:" + memoryPoolMXBean.getUsage()
                    .getCommitted()
                    + "   used:" + memoryPoolMXBean.getUsage().getUsed());
        }
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String main2() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString().intern();

    }
}
