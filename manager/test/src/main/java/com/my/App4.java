package com.my;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Hello world!
 */
public class App4 {
    static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
//        while (true) {
//            main2();
//        }

        int i=0;
        try {
            for(int j=0;j<1000000;j++){
                String.valueOf(j).intern();
                i++;
            }
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(i);
        }
    }

    public static String main2() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString().intern();

    }
}
