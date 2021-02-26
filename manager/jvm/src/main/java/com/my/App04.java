package com.my;

/**
 * Hello world!
 */
public class App04 implements IMy01, IMy02 {
    private int id;
    private int age;

    public void func() {
    }

    public void func2() {
    }

    @Override
    public int say01() {
        return 0;
    }

    @Override
    public String say02() {
        return "say02";
    }
}
