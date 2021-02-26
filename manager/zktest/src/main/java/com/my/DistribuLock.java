package com.my;

import javafx.collections.transformation.SortedList;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

public class DistribuLock {
    ZooKeeper zooKeeper;
    CountDownLatch latch = new CountDownLatch(1);

    private String currentNode = "";

    public DistribuLock() {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.zooKeeper = new ZooKeeper("192.168.77.135:2181", 4000, new Watcher() {
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
    }

    public static ZooKeeper create() {
        ZooKeeper zooKeeper = null;
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper("192.168.77.135:2181", 4000, new Watcher() {
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


    public void lock() {
        currentNode = createLockNode();
        observerChild();
        try {
            latch.await();
            System.out.println(Thread.currentThread().getName() + ":" + currentNode + ":获得锁。。。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unLock() {
        try {
            Stat stat = zooKeeper.exists("/lock/" + currentNode, false);
            if (stat != null) {
                System.out.println(Thread.currentThread().getName() + ":" + currentNode + ":释放锁。。。。。。。。。。。。");

                zooKeeper.delete("/lock/" + currentNode, stat.getVersion(), (rc, path, ctx) -> {
                    System.out.println(path + ":delete success");
                    try {
                        Thread.sleep(1000 * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("rc->" + rc);
                    try {
                        zooKeeper.close();
                    } catch (InterruptedException ex) {
                    }
                }, "ctx");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void observerChild() {

        try {
            List<String> childrens = zooKeeper.getChildren("/lock", false);
            List<String> collect = childrens.stream().filter(f -> f.compareTo(currentNode) < 0).collect(Collectors.toList());
            if (collect == null || collect.size() < 1) {
                latch.countDown();
            } else {
                collect.sort(String::compareTo);
                String s = collect.get(collect.size() - 1);
                zooKeeper.exists("/lock/" + s, event -> {
                    System.out.println(event.getType() + ";" + event.getPath());
                    switch (event.getType()) {
                        case NodeDeleted:
                            observerChild();
                            break;
                    }
                });
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String createLockNode() {
        try {
            String s = zooKeeper.create("/lock/", "".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            return s.substring(6);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
