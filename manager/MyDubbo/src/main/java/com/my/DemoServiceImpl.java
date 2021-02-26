package com.my;


import com.my.DemoService;

public class DemoServiceImpl implements DemoService {
    public String say(String name) {
        return "Hello " + name;
    }
}
