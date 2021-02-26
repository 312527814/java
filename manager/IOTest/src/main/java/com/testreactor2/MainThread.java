package com.testreactor2;

public class MainThread {
    static ThreadLocal<String> local = new ThreadLocal<>();

    static MyThreadLocal myLocal = new MyThreadLocal();

    public static void main(String[] args) {


        String s = myLocal.get();
        SelectorThreadGroup boss = new SelectorThreadGroup(1, "boss");

        SelectorThreadGroup work = new SelectorThreadGroup(3, "work");
        boss.setWork(work);
        boss.bind(9090);
    }


    static class MyThreadLocal extends ThreadLocal<String> {
        @Override
        protected String initialValue() {
            return "dsdsd大萨达所";
        }
    }
}
