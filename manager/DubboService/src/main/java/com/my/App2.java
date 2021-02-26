package com.my;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App2 {
    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/provider2.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
