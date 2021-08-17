package com.my.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"dubbo/dubbo-demo-provider.xml","dubbo/dubbo-demo-registry.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}
