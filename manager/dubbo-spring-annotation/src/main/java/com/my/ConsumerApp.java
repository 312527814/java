package com.my;

import com.my.api.DemoService;
import com.my.config.ConsumeConfig;
import com.my.serviceconsumer.DemoConsumerService;
import com.my.serviceconsumer.IDemoConsumerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;

public class ConsumerApp {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumeConfig.class);
        context.start();
        IDemoConsumerService bean = context.getBean(IDemoConsumerService.class);
//        DemoService bean1 = context.getBean(DemoService.class);
//        String dsds = bean1.sayHello("dsds");

        String hello_word = bean.test("hello word");
        System.out.println(hello_word);
        new CountDownLatch(1).await();
    }
}
