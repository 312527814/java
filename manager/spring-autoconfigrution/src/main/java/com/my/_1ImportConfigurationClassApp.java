package com.my;

import com.my.bean.Person;
import com.my.config.MyConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
@Import(MyConfiguration.class)
public class _1ImportConfigurationClassApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("ImportConfigurationClassApplicationContext.xml");
        Person bean = ac.getBean(Person.class);

        System.out.println("person " + bean.getName());
    }
}
