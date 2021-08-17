package com.my.proxy.cglib;

import org.springframework.stereotype.Component;

@Component
public class CglibBoss implements IBoss {
    public  void Say(String message) {
        System.out.println("hello " + message);
    }

}
