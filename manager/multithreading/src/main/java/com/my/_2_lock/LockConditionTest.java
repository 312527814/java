package com.my._2_lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年06月25⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
public class LockConditionTest {
    private LinkedList<String> queue=new LinkedList<String>();

    private Lock lock = new ReentrantLock();

    private int maxSize = 5;

    private Condition providerCondition = lock.newCondition();

    private Condition consumerCondition = lock.newCondition();

    public void provide(String value){
        try {
            lock.lock();
            System.out.println("provide.size():"+queue.size());
            while (queue.size() == maxSize) {
                providerCondition.await();
            }
            queue.add(value);
            consumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String consume(){
        String result = null;
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "：consume.size():" + queue.size());
            while (queue.size() == 0) {
                consumerCondition.await();
            }
            result = queue.poll();
            providerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }

    public static void main(String[] args) {
        LockConditionTest t = new LockConditionTest();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                t.provide(i+"");
            }

        }).start();
        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                t.consume();
            }

        },"c1").start();

        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                t.consume();
            }

        },"c2").start();


    }
}
