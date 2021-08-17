package com.my;

import com.my.service.MyService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAdvisorApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("advisorApplicationContext.xml");
        MyService bean = ac.getBean(MyService.class);
        int run = bean.run();
//        Person person = ac.getBean("person", Person.class);
//        ac.publishEvent(new LoginEvent("this is loginEvent \r\n"));
    }
}
