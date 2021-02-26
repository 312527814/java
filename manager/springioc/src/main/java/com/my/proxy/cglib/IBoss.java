package com.my.proxy.cglib;

import org.springframework.stereotype.Component;

@Component
public interface IBoss {
    void Say(String message);
}
