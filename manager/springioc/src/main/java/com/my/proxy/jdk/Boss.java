package com.my.proxy.jdk;

import org.springframework.stereotype.Component;

@Component
public class Boss implements  IBoss {
    @Override
    public void Do(String str) {
        System.out.println(str);
    }
}
