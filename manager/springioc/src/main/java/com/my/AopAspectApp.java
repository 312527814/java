package com.my;

import com.my.servlet.BaseServlet;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class AopAspectApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aspectApplicationContext.xml");
        BaseServlet myServlet = ac.getBean("myServlet", BaseServlet.class);
        ConfigurableEnvironment environment = ac.getEnvironment();
        myServlet.run();
    }
}
