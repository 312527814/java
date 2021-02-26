package com.my.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        //-Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
        System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("total memory:" + Runtime.getRuntime().totalMemory());
        List<byte[]> s=new ArrayList<>();
        for (int i = 0; i <15 ; i++) {
            byte[] b1 = new byte[ 1024 * 1024];
            s.add(b1);
        }

        System.out.println("分配了1M");
        System.out.println("max memory:" + Runtime.getRuntime().maxMemory());
        System.out.println("free memory:" + Runtime.getRuntime().freeMemory());
        System.out.println("total memory:" + Runtime.getRuntime().totalMemory());
    }
}
