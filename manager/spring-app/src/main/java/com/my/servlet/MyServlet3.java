package com.my.servlet;

import com.my.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//@Component
public class MyServlet3  {
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Autowired
    private Person person;

    @Value("${a}")
    private  String name;
    public MyServlet3(){
    }

    public  void testAop(){
        System.out.println(person);
        System.out.println("testAop exeing");
    }


}
