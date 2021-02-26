package com.my;


public class MyApp3 {
    public static void main(String[] args) throws Exception {

		System.out.print(MyApp3.class.getClassLoader());
//        Class<?> aClass = Class.forName("com.my.MyAppTest", true, MyApp3.class.getClassLoader());
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver", true, MyApp3.class.getClassLoader());
        System.out.print("hello word"+aClass);

        System.out.print("  getClassLoader:"+aClass.getClassLoader());
    }
}
