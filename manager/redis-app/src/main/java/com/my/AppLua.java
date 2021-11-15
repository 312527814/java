package com.my;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * Hello world!
 */
public class AppLua {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    @Test
    public void testLua() {
        Jedis jedis = new Jedis("192.168.77.135", 6379);
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList("f"),
                    Collections.singletonList("2"));
            System.out.println(result);
        }finally {
            jedis.close();
        }

    }

    @Test
    public void testList() {
        Jedis jedis = new Jedis("192.168.77.135", 6379);
        Card card = new Card();
        card.setId(123455);
        card.setPwd("2222");
        Long dental_card = jedis.lpush("dental_card", JSONObject.toJSONString(card));
        System.out.println(dental_card);

    }

    @Test
    public void testListPop() {
        Jedis jedis = new Jedis("192.168.77.135", 6379);
        Card card = new Card();
        card.setId(123455);
        card.setPwd("2222");
        String dental_card = jedis.lpop("dental_card");
        Object parse = JSONObject.parse(dental_card);
        System.out.println(dental_card);

    }

    static class Card {

        private int id;
        private String pwd;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
}
