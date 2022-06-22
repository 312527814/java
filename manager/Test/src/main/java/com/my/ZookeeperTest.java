package com.my;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-11-11 00:00
 */
public class ZookeeperTest {

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = create();
        CountDownLatch latch = new CountDownLatch(1);
        zooKeeper.create("/lock/", "".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,(rc, path, ctx, name) -> {
            System.out.println("rc:" +rc);
            System.out.println("path:" +path);
            System.out.println("ctx:" +ctx);
            System.out.println("name:" +name);
            latch.countDown();

        },"12345");

        latch.await();
        System.out.println("............................");
        String currentNode = zooKeeper.create("/lock/", "".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        currentNode=currentNode.substring(6);
        Stat stat = zooKeeper.exists("/lock/" + currentNode, false);


        AtomicBoolean isSuccess= new AtomicBoolean(true);
        CountDownLatch delete_latch = new CountDownLatch(1);
        zooKeeper.delete("/lock/" +currentNode, stat.getVersion(), (rc, path, ctx) -> {
            System.out.println(path + ":delete success");
            System.out.println("rc->" + rc);

            try {
                zooKeeper.close();
            } catch (InterruptedException ex) {
            }
            if(rc!=0) {
                isSuccess.set(false);
            }
            delete_latch.countDown();
        }, "ctx");
        delete_latch.await();
        if(!isSuccess.get()){
            throw  new RuntimeException("锁丢失");
        }

//
    }


    private String currentNode = "";

    public static ZooKeeper create() {
        ZooKeeper zooKeeper = null;
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper("192.168.247.128:2181", 4000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    switch (watchedEvent.getState()) {
                        case SyncConnected:
                            countDownLatch.countDown();
                            break;
                    }
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return zooKeeper;
    }





}
