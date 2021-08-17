package com.my.dubbo;

import com.my.dubbo.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo/dubbo-demo-consumer.xml","dubbo/dubbo-demo-registry.xml"});
        context.start();
        while (true){
            System.out.println("等待输入....");
            System.in.read();
            try {
                DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
                String hello = demoService.sayHello("world"); // 执行远程方法
                System.out.println("............" +hello+"............" ); // 显示调用结果

            }catch (Exception e){
                e.printStackTrace();
            }


        }


//        new CountDownLatch(1).await();
    }
}
