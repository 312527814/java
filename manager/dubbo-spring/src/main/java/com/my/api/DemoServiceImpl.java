package com.my.api;

import com.alibaba.dubbo.config.ProtocolConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoServiceImpl implements DemoService, ApplicationContextAware {

    private  ApplicationContext applicationContext;

    @Autowired
    private ProtocolConfig config;
    public String sayHello(String name) {
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
