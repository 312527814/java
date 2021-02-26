package com.my.jvm;

import java.util.ArrayList;
import java.util.List;

public class Test02 {
    static  int i=0;
    public static void main(String[] args) {

        //-Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC  -Xss1M -XX:NewRatio=4 -XX:SurvivorRatio=2

        ss();

    }

    private  static  void  ss(){

        try{
            i++;
            ss();

        }catch (StackOverflowError e){
            System.out.println("i:"+i);
           // e.printStackTrace();

        }
    }


}
