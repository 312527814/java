package com.my;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        for (int i = 0; i <3 ; i++) {
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入。。");
            String str ="";
            if (scan.hasNext()) {
                str = scan.next();
                System.out.println("输入的数据为：" + str);
            }
            scan.close();
        }

        //scan.close();
    }
}
