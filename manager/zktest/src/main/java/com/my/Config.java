package com.my;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Config {

    private static List<String> childs = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = ZKFactory.create();
        getChildren(zooKeeper, "/config");
        while (true) {
            Thread.sleep(1000 * 2);
            AtomicReference<String> ss = new AtomicReference<>("");
            childs.forEach(f -> {
                ss.set(ss + "  " + f);
            });
            System.out.println("childs=>" + ss.get());

        }

    }

    public static void getChildren(ZooKeeper zooKeeper, String path) {
        zooKeeper.getChildren(path, event -> {
            System.out.println("getType=>" + event.getType());
            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                getChildren(zooKeeper, path);
            }
        }, new AsyncCallback.ChildrenCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children) {
                childs.clear();
                children.forEach(f -> {
                    childs.add(f);
                });
            }
        }, "");

    }
}
