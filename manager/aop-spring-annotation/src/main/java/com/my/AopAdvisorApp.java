package com.my;

import com.my.advisor.annotation.MyTransactional;
import com.my.config.AdvisorConfig;
import com.my.config.AspectConfig;
import com.my.service.MyService;
import com.my.service.MyServiceImpl;
import org.junit.Test;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AopAdvisorApp {

    @Test
    public void xml() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("advisorApplicationContext.xml");
        MyService bean = ac.getBean(MyService.class);
        int run = bean.run();
    }

    @Test
    public void noXml() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(AdvisorConfig.class);
        ac.refresh();
        MyService bean = ac.getBean(MyService.class);
        int run = bean.run();
    }
}
