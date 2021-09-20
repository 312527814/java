package com.my;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.remoting.exchange.ResponseCallback;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.protocol.dubbo.FutureAdapter;
import com.my.api.DemoService;
import com.my.api.DemoService2;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AppConsumer {
    public static void main(String[] args) {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("appConsumer-of-app");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://192.168.77.130:2181");
        registry.setTimeout(50000);
        registry.setGroup("dubbo-app");


// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

// 引用远程服务
        {
            ReferenceConfig<DemoService> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
            reference.setApplication(application);
            reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
            reference.setInterface(DemoService.class);
            reference.setLoadbalance("roundrobin");

            reference.setVersion("1.0.0");
            reference.setTimeout(3234);
            reference.setCheck(false);
            reference.setRetries(0);
            reference.setAsync(true);
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put("enabled","false");
//            parameters.put("connections","1");
//            reference.setParameters(parameters);
// 和本地bean一样使用xxxService
            DemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
            for (int i = 0; i < 1; i++) {
                String s2 = demoService.sayHello("hello word..2");
                Future<Object> future = RpcContext.getContext().getFuture();

                FutureAdapter<Object> o = (FutureAdapter) future;
                o.getFuture().setCallback(new ResponseCallback() {
                    @Override
                    public void done(Object response) {
                        RpcResult result = (RpcResult) response;
                        System.out.println("---calback-----------result:" + result.getValue());
                    }

                    @Override
                    public void caught(Throwable exception) {

                    }
                });
                System.out.println("--------------result:" + s2);
                try {
                    new CountDownLatch(1).await();
                } catch (Exception e) {

                }

            }
        }

//        {
//            ReferenceConfig<DemoService2> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
//            reference.setApplication(application);
//            reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
//            reference.setInterface(DemoService2.class);
//            reference.setLoadbalance("roundrobin");
//            reference.setVersion("1.0.0");
//            reference.setTimeout(1000 * 3);
//
//// 和本地bean一样使用xxxService
//            DemoService2 demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
//            for (int i = 0; i < 1; i++) {
//                String s2 = demoService.sayHello("hello word..2");
//                System.out.println(s2);
//            }
//        }


    }

    @Test
    public void asyn(){
        ApplicationConfig application = new ApplicationConfig();
        application.setName("appConsumer-of-app");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://192.168.77.130:2181");
        registry.setTimeout(50000);
        registry.setGroup("dubbo-app");


// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

// 引用远程服务
        {
            ReferenceConfig<DemoService> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
            reference.setApplication(application);
            reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
            reference.setInterface(DemoService.class);
            reference.setLoadbalance("roundrobin");

            reference.setVersion("1.0.0");
            reference.setTimeout(3234);
            reference.setCheck(false);
            reference.setRetries(0);
            reference.setAsync(true);
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put("enabled","false");
//            parameters.put("connections","1");
//            reference.setParameters(parameters);
// 和本地bean一样使用xxxService
            DemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
            for (int i = 0; i < 1; i++) {
                String s2 = demoService.sayHello("hello word..2");
                Future<Object> future = RpcContext.getContext().getFuture();

                FutureAdapter<Object> o = (FutureAdapter) future;
                o.getFuture().setCallback(new ResponseCallback() {
                    @Override
                    public void done(Object response) {
                        RpcResult result = (RpcResult) response;
                        System.out.println("---calback-----------result:" + result.getValue());
                    }

                    @Override
                    public void caught(Throwable exception) {

                    }
                });
                System.out.println("--------------result:" + s2);
                try {
                    new CountDownLatch(1).await();
                } catch (Exception e) {

                }

            }
        }

    }
}
