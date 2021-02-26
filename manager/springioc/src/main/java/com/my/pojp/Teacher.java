package com.my.pojp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class Teacher {
    private String name;
    private int id;
    @Autowired
    private Flower flower;
}
