package com.my.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-28 20:59
 */
public class Test {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
