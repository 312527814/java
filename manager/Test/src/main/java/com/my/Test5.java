package com.my;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class Test5 {

    public static void main(String[] args) throws Exception {

        MultiValueMap<String, LinkedList> urlLookup = new LinkedMultiValueMap();
        LinkedList<String> list=new LinkedList<>();
        list.add("aa");
        urlLookup.add("a",list);
        List<LinkedList> a = urlLookup.get("a");

    }

    public static void t() {
        Person person1 = new Person();
    }

    static class Person {
        private int id = 1;
        private String name = "张三";
    }

}




