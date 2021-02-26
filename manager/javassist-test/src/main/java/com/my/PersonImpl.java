package com.my;

public class PersonImpl implements Person {
    static {
        System.out.println("PersonImpl.........");
    }

    @Override
    public void say() {
        System.out.println("hello");
    }
}
