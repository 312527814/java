package com.my.classloader;


import com.my.App01;
import com.my.App02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class T012_ClassReloading2 {
    private static class MyLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {

            File f = new File("H:/javalianxi/manager/target/classes/" + name.replace(".", "/").concat(".class"));

            if (!f.exists()) {
                return super.loadClass(name);
            }

            try {

                InputStream is = new FileInputStream(f);

                byte[] b = new byte[is.available()];
                is.read(b);
                Class<?> aClass = defineClass(name, b, 0, b.length);
                resolveClass(aClass);
                return aClass;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return super.loadClass(name);
        }


    }

    public static void main(String[] args) throws Exception {
        System.out.println(App01.name);
        System.out.println(App01.id);
//        MyLoader m = new MyLoader();
//        Class clazz = m.loadClass("com.my.MyTest01");
//        Object o = clazz.newInstance();
//        m = new MyLoader();
//        Class clazzNew = m.loadClass("com.my.MyTest01");
//
//        System.out.println(clazz == clazzNew);
    }
}
