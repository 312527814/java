package com.my;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
public class App2 {
    public static void main(String[] args) throws Exception {

        ZooKeeper zooKeeper = create();
//        String s = zooKeeper.create("/lock/", "".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);


        zooKeeper.exists("/lock", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("huidao");
            }
        });
        System.in.read();

    }

    public static String ss(ZooKeeper zooKeeper) {
        String s = "";
        try {
            s = zooKeeper.create("/lock/", "".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            Stat exists = zooKeeper.exists(s, false);
            zooKeeper.delete(s, exists.getVersion(), new AsyncCallback.VoidCallback() {
                @Override
                public void processResult(int rc, String path, Object ctx) {
                    System.out.println(rc + ":" + path + ":delete");
                }
            }, "");

        } catch (Exception e) {
            try {
                zooKeeper.close();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            ZooKeeper zooKeeper1 = create();
            ss(zooKeeper1);
        }
        return s;
    }


    public static ZooKeeper create() {
        ZooKeeper zooKeeper = null;
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper("localhost:2181", 4000, new Watcher() {
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
