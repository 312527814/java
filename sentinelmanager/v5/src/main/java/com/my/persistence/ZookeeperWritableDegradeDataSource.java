package com.my.persistence;

import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.fastjson.JSON;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-11-01 11:00
 */
public class ZookeeperWritableDegradeDataSource implements WritableDataSource {
    private int RETRY_TIMES = 3;
    private int SLEEP_TIME = 1000;

    private String remoteAddress = "192.168.77.135:2181";
    private int connectionTimeoutMs = 15 * 1000;
    private int sessionTimeoutMs = 60 * 1000;

    CuratorFramework zkClient;

    public ZookeeperWritableDegradeDataSource() {
        zkClient = CuratorFrameworkFactory.newClient(remoteAddress, sessionTimeoutMs, connectionTimeoutMs, new ExponentialBackoffRetry(SLEEP_TIME, RETRY_TIMES));
    }

    @Override
    public void write(Object value) throws Exception {
        String path = "/sentinel/degrade-rule";
        String rule = JSON.toJSONString(value);
        CuratorFrameworkState state = zkClient.getState();
        if (state != CuratorFrameworkState.STARTED) {
            zkClient.start();
        }

        Stat stat = zkClient.checkExists().forPath(path);
        if (stat == null) {
            zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, null);
        }
        zkClient.setData().forPath(path, rule.getBytes());
    }

    @Override
    public void close() throws Exception {
        zkClient.close();
    }
}
