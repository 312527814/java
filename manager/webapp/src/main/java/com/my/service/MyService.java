package com.my.service;

import org.springframework.stereotype.Service;

@Service
public class MyService implements  IMyService {
    public String alive() {
        return "hello word";
    }
}
