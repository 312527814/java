package com.my;

import com.my.service.MyService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class AopAspectApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aspectApplicationContext.xml");
        MyService bean = ac.getBean(MyService.class);
        int run = bean.run();
    }
}
