package com.my.services;


import com.my.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DemoConsumerService2 implements  IDemoConsumerService2{
    @Autowired
    private DemoService demoService;


    @Override
    public  String  test(String str){
       return demoService.sayHello(str);
    }
}
