package com.my.spi;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-08-16 18:54
 */
public class MySpi1 implements MySpi {
    public MySpi1(){
        System.out.println("myspi create...");
    }
    @Override
    public void say() {
        System.out.println("我是spi 1");
    }
}
