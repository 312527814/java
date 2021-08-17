package com.my;

import com.my.config.AspectConfig;
import com.my.service.MyService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAspectApp {

    @Test
    public void xml(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aspectApplicationContext.xml");
        MyService bean = ac.getBean(MyService.class);
        int run = bean.run();
    }

    @Test
    public  void noXml(){

        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        ac.register(AspectConfig.class);
        ac.refresh();
        MyService bean = ac.getBean(MyService.class);
        int run = bean.run();
    }
}
