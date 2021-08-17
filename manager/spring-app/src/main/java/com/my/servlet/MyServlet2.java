package com.my.servlet;

import com.my.pojp.Person;

import javax.annotation.Resource;

//@Component
public class MyServlet2 implements BaseServlet {
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Resource(name = "factoryBeanTest")
    private Person person;

    public MyServlet2() {
    }

    public void testAop() {
        System.out.println(person);
        System.out.println("testAop exeing");
    }

    @Override
    public void run() {
        System.out.println("MyServlet2 run");
    }


}
