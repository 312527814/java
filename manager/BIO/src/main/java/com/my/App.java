package com.my;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        CountDownLatch count = new CountDownLatch(1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(new Date().getTime());
                count.countDown();
            }
        });

        thread.start();

        try{
            count.await();
        }catch (Exception e){

        }

        thread.getState();
        thread.start();

        System.out.println( "Hello World!" );
    }
}
