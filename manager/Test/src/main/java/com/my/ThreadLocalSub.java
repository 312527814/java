package com.my;

public class ThreadLocalSub<T> extends ThreadLocal<T> {
    @Override
    protected void finalize() throws Throwable {
        System.out.print("ThreadLocalSub finalize");
    }
}
