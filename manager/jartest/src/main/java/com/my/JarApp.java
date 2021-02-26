package com.my;

/**
 * Hello world!
 */
public class JarApp {
    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        new Thread(() -> {
            System.out.println("2:" + Thread.currentThread().getContextClassLoader());
        }).start();
        JarApp jarApp = new JarApp();
        System.out.println("Hello World! 1:" + contextClassLoader);
    }
}
