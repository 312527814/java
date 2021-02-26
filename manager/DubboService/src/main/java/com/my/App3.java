package com.my;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@DubboComponentScan(basePackages = "com.my.w")
public class App3 {
    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/provider3.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
