package com.my.service;

import com.my.dubbo.api.DemoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class MyService implements ApplicationContextAware {
    @Autowired
    DemoService demoService;

    public String test() {
        String world = demoService.sayHello("world");
        return  world;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        Object demoService = applicationContext.getBean("demoService");
//        this.demoService=(DemoService)demoService;
    }
}
