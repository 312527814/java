package com.my;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyTest2 {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        System.out.println("begin");
        DebugBean debugBean = new DebugBean();

        //DebugBean2 debugBean2 = new DebugBean2();
        debugBean.setId(123);
        for (int i = 0; i < 100; i++) {
            debugBean.setId(123);
        }
        System.out.println("end");

    }


}
