package com.my;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) throws Exception {

        Thread.yield();

//        List<Thread> threads = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            threads.add(new Thread(() -> {
//                DistribuLock lock = new DistribuLock();
//
//                lock.lock();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                lock.unLock();
//            }, "th-" + i));
//        }
//
//        threads.forEach(f -> {
//            f.start();
//        });

        ZooKeeper zooKeeper = DistribuLock.create();
        System.out.println("Create");
        String s = zooKeeper.create("/lock/", "".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);


        System.out.println(s);
//        Thread.sleep(2000 * 5);
//        List<String> children = zooKeeper.getChildren("/", false);

        System.out.println("end");
        System.in.read();

    }
}
