package com.my.pojp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Person implements InitializingBean {

    public Person() {
        System.out.print("person");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer id;
    private String name;

//    public void afterPropertiesSet() {
//        System.out.print("afterPropertiesSet");
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("afterPropertiesSet");
    }
}
