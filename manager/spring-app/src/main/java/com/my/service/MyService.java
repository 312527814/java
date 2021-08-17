package com.my.service;

import com.my.pojp.Flower;

import java.util.List;

public interface MyService {
    List<Flower> fun();
    int insert() throws Exception;
}
