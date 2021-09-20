package com.my;

import com.my.bean.Person;
import com.my.config.MyImportSelector;
import com.my.config.MyImportSelectorAuto;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
@Import(MyImportSelectorAuto.class)
public class _4AutoImportSelectorApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("AutoImportSelectorApplicationContext.xml");
        Person bean = ac.getBean(Person.class);

        System.out.println("person " + bean.getName());
    }
}
