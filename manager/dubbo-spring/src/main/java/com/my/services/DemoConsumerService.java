package com.my.services;


import com.my.api.DemoService;
import org.springframework.stereotype.Service;

public class DemoConsumerService implements  IDemoConsumerService{
    private DemoService demoService;

    public DemoConsumerService(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public  String  test(String str){
       return demoService.sayHello(str);
    }
}
