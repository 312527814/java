package com.my;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年06月22⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
public class RedisLock {

    /*Redisson的配置类*/
    private static RedissonClient redissonClient() {
        Config config = new Config();
        /* Redis 单节点*/
        config.useSingleServer().setAddress("redis://192.168.247.128:6379");
        return Redisson.create(config);
    }

     public static void main(String[] args) throws InterruptedException {
         RedissonClient redissonClient = redissonClient();
         RLock lock = redissonClient.getLock("aaa");
         RLock lock2 = redissonClient.getLock("aaa");
         lock.lock();
         lock2.lock();

         //Thread.sleep(10000);
         lock.unlock();
     }
}
