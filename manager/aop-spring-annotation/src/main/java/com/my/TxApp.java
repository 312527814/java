package com.my;

import com.my.config.AdvisorConfig;
import com.my.config.TxConfig;
import com.my.service.MyService;
import com.my.servicetx.MyTxService;
import org.junit.Test;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxApp {
    public void xml(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("txApplicationContext.xml");
        MyTxService bean = ac.getBean(MyTxService.class);
        int run = bean.insert();
    }

    @Test
    public void  noXml(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(TxConfig.class);
        ac.refresh();
        MyTxService bean = ac.getBean(MyTxService.class);
        int run = bean.insert();
    }
}
