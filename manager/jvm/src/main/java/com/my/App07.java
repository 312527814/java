package com.my;

/**
 * Hello world!
 */
public class App07 {
    static Object o = null;

    public static void main(String[] args) {

        Father test = new Son();
        test.fMe();//编译时指向父类中国的fMe()，在运行时由于是invokevirtual调用，因此test将变成实际类型Son，如果Son中有Fme()，就调用Son自己的，若没有就调用父类的
    }


}
