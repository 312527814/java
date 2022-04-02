package com.my;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
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
    public void testHash() {
        Jedis jedis = new Jedis("10.16.208.93", 7103);
        Card card = new Card();
        card.setId(123455);
        card.setPwd("2222");
        byte[] bytes = toByteArray(card);

        Long dental_card = jedis.hset("gujzi_card".getBytes(),"test".getBytes(), bytes);
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

    public byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    static class Card implements Serializable {

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
