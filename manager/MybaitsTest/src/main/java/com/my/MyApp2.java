package com.my;




public class MyApp2 {
    public static void main(String[] args) throws Exception {

       // Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver", true, MyApp2.class.getClassLoader().getParent());
        Class<?> MyApp2 = Class.forName("com.my.MyAppTest", true, MyApp2.class.getClassLoader());
        System.out.print("hello word"+MyApp2);




    }
}
