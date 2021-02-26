package com.my;

import com.my.pojp.FactoryBeanTest;
import com.my.pojp.Person;
import com.my.servlet.BaseServlet;
import com.my.servlet.MyServlet;
import com.my.servlet.MyServlet3;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 */
public class SpringiocApp {
    public static void main(String[] args) {

//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "e:\\class");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        for (String s : ac.getBeanDefinitionNames()) {
            System.out.println("\r\n" + s);
        }



        Object people2 = ac.getBean("flower");
        Object people = ac.getBean("teacher");
        Object factoryBeanTest = ac.getBean("factoryBeanTest");
        Object factoryBeanTest2 = ac.getBean("factoryBeanTest");
        Object factoryBean = ac.getBean("&factoryBeanTest");

        Object pp = ac.getBeansOfType(Person.class);
        Object fac = ac.getBean(FactoryBeanTest.class);

//


    }


}
