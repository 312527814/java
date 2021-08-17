package com.my;

import java.lang.reflect.Constructor;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-07-16 14:21
 */
public class Persion implements  IPersion {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    /** Returns the number of shared holds represented in count  */
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
    /** Returns the number of exclusive holds represented in count  */
    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

    public void  add(){
        System.out.println("add....");
    }

    public static void main(String[] args) {

        Create(Persion.class);
    }

    public static  void   Create(Class aclass){

        try {
            if( IPersion.class.isAssignableFrom(aclass)) {
                IPersion o = (IPersion)aclass.newInstance();
                o.add();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
