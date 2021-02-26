package com.my;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.monitor.MonitorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    @Configuration
    @EnableDubbo(scanBasePackages = "com.my")
    @PropertySource("classpath:/dubbo-provider.properties")
    @ComponentScan(value = {"com.my"})
    public static  class ProviderConfiguration {

    }

    public static void main(String[] args) throws IOException {
        //SpringApplication.run(App.class, args);
        //加载xml配置文件启动
       // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/provider.xml");

        AnnotationConfigApplicationContext  context =new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        TestDemo bean = context.getBean(TestDemo.class);
        System.in.read(); // 按任意键退出
    }
}
