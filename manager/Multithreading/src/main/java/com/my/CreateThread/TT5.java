package com.my.CreateThread;

import java.util.concurrent.locks.LockSupport;

public class TT5 {
    public static volatile YieldTest yt1;

    public static void main(String[] args) throws InterruptedException {
        yt1 = new YieldTest("张三");
        YieldTest yt2 = new YieldTest("李四");
        yt1.start();
        yt2.start();
    }
}

class YieldTest extends Thread {
    public YieldTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
                System.out.println(this.getName() + "before yield 状态-----" + this.getState());
//                this.yield();

                this.suspend();
                System.out.println(this.getName() + "after yield 状态-----" + this.getState());
            }

            if (this.getName() == "李四") {
                State state = TT5.yt1.getState();
                 System.out.println("张三状态-----" + state);
            }
        }
    }
}
