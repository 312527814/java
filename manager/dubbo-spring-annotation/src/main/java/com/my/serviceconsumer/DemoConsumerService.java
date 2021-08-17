package com.my.serviceconsumer;


import com.alibaba.dubbo.config.annotation.Reference;
import com.my.api.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoConsumerService implements  IDemoConsumerService{
    @Reference
    private DemoService demoService;
    @Override
    public  String  test(String str){
       return demoService.sayHello(str);
    }
}
