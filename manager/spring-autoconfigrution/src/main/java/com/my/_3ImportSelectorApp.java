package com.my;

import com.my.bean.Person;
import com.my.config.MyConfiguration;
import com.my.config.MyImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
@Import(MyImportSelector.class)
public class _3ImportSelectorApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("ImportSelectorApplicationContext.xml");
        Person bean = ac.getBean(Person.class);

        System.out.println("person " + bean.getName());
    }
}
