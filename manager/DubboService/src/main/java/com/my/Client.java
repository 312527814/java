package com.my;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("META-INF/consume.xml");
        context.start();
        TestDemo providerService = (TestDemo) context.getBean("providerService");
//        String str = providerService.fun("hello");
//        String str2 = providerService.fun("hello");
//com.alibaba.dubbo.monitor.MonitorService
        for (int i = 0; i <20; i++) {
            Thread.sleep(500);
            String str = providerService.fun("hello");
            System.out.println(str);
        }
//        System.out.println(str);
//        System.out.println(str2);
        System.in.read();
    }
}
