package com.my;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        File xFile=new File("E:\\CompanyProject\\pmp-api\\target\\pmp-api.jar");
        URL xUrl= xFile.toURL() ;

        URLClassLoader classLoader = new URLClassLoader(new URL[]{xUrl});
        Class<?> mainClass = classLoader.loadClass("org.springframework.boot.loader.JarLauncher");

        String[]  str = {"大王","小王"};
        Thread.currentThread().setContextClassLoader(classLoader);
        Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
        mainMethod.invoke( null, (Object) str);

    }


}
