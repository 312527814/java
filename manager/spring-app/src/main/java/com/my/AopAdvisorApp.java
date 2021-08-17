package com.my;

import com.my.servlet.BaseServlet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAdvisorApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("advisorApplicationContext.xml");
        BaseServlet myServlet = ac.getBean("myServlet", BaseServlet.class);
//        Person person = ac.getBean("person", Person.class);
//        ac.publishEvent(new LoginEvent("this is loginEvent \r\n"));
        myServlet.run();
    }
}
