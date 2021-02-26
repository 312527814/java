package com.my;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();

        genericObjectPoolConfig.setMaxTotal(10);
        genericObjectPoolConfig.setMinIdle(2);
        genericObjectPoolConfig.setMaxIdle(2);
        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig, "192.168.77.130", 6379);

        Jedis client = jedisPool.getResource();
//        for (int i = 0; i <100; i++) {
//            client.zadd("online", 100 + i, "000" + i);
//        }
        String dd = client.set("dd", "ddd");

        Set<String> online = client.zrevrangeByScore("online", 101, 0);
       // Long online1 = client.zremrangeByLex("online", "101", "0");
        Long online2 = client.zremrangeByRank("online", 101, 0);
        System.out.println("Hello World!");
    }
}
