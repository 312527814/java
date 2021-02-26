package com.my;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main(String[] args) throws InterruptedException {

        final BooleanLock booleanLock = new BooleanLock();

        //创建4个线程
        for (int i = 1; i < 5; i++) {

            new Thread("T" + i) {
                @Override
                public void run() {
                    try {
                        booleanLock.lock();
                        System.out.println(Thread.currentThread().getName() + " have the lock Monitor");
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        booleanLock.unlock();
                    }
                };
            }.start();
        }

    }
    private static void work() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is Working...");
        Thread.sleep(4_000);
    }


}
