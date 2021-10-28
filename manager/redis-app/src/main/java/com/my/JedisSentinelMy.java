package com.my;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JedisSentinelMy {
    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                Jedis jedis = getJedis();

                Pipeline pipelined = jedis.pipelined();
                Transaction multi = jedis.multi();

                System.out.println(jedis.set("sentinel", "zhuge"));
                System.out.println(jedis.get("sentinel"));
                System.out.println(jedis.getClient().getPort());
                System.out.println("................................");
                Thread.sleep(2000);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public static Jedis getJedis() {

        Jedis sentinel = new Jedis("192.168.77.135", 26379);
        List<Map<String, String>> slaves = sentinel.sentinelSlaves("mymaster");
        List<String> list = sentinel.sentinelGetMasterAddrByName("mymaster");
        String host = list.get(0);
        String port = list.get(1);
        Jedis jedis = new Jedis(host, Integer.parseInt(port));
        return jedis;


    }
}
