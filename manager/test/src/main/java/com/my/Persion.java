package com.my;

import java.lang.reflect.Constructor;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-07-16 14:21
 */
public class Persion implements IPersion {

    static final int SHARED_SHIFT = 16;
    static final int SHARED_UNIT = (1 << SHARED_SHIFT);
    static final int MAX_COUNT = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    public String getName() {
        return name;
    }

    /**
     * Returns the number of shared holds represented in count
     */
    private  String name;

    public void setName(String name) {
        this.name = name;
    }

    static int sharedCount(int c) {
        return c >>> SHARED_SHIFT;
    }

    /**
     * Returns the number of exclusive holds represented in count
     */
    static int exclusiveCount(int c) {
        return c & EXCLUSIVE_MASK;
    }

    public void add() {
        System.out.println("add....");
    }

    public static void main(String[] args) {

        // Create(Persion.class);
        Persion persion1 = new Persion();
        persion1.setName("persion1");
        for (int i = 0; i < 1000000000; i++) {
            Persion persion = new Persion();

            System.out.println("..........." + i);
        }

    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("回收了。。。");
    }

    public static void Create(Class aclass) {

        try {
            if (IPersion.class.isAssignableFrom(aclass)) {
                IPersion o = (IPersion) aclass.newInstance();
                o.add();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
