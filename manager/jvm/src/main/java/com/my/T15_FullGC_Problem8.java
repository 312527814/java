package com.my;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T15_FullGC_Problem8 {

    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthdate = new Date();
    }

   public  static List<CardInfo> list=new ArrayList<>();


    public static void main(String[] args) throws Exception {

        for (;;){
            List<CardInfo> allCardInfo = getAllCardInfo();
            list.addAll(allCardInfo);
           Thread.sleep(20);
        }

    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }

        return taskList;
    }
}
