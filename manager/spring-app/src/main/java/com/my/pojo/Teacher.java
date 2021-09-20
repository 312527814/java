package com.my.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Scope("prototype")
@Component
public class Teacher {
    private String name;
    private int id;
    @Autowired
    private Flower flower;
}
