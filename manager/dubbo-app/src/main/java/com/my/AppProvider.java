package com.my;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.my.api.DemoService;
import com.my.api.DemoService2;
import com.my.api.DemoServiceImpl;
import com.my.api.DemoServiceImpl2;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class AppProvider {
    @Test
    public void main() throws InterruptedException {
        main1();
//        main2();
        new CountDownLatch(1).await();
    }


    public void main1() throws InterruptedException {


// 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("provider-of-app");
        application.setLogger("log4j");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setGroup("dubbo-app");
        registry.setTimeout(50000);
        registry.setAddress("zookeeper://192.168.77.130:2181");
//        registry.setUsername("aaa");
//        registry.setPassword("bbb");

// 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20882);
        protocol.setThreads(200);

// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

// 服务提供者暴露服务配置
        {
            // 服务实现
            DemoServiceImpl demoService = new DemoServiceImpl();
            demoService.setConfig(protocol);
            ServiceConfig<DemoService> service = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
            service.setApplication(application);
            service.setRegistry(registry); // 多个注册中心可以用setRegistries()
            service.setProtocol(protocol); // 多个协议可以用setProtocols()
            service.setInterface(DemoService.class);
            service.setRef(demoService);
            service.setVersion("1.0.0");
            service.setTimeout(2000);
            service.setFilter("MyFilter2");

// 暴露及注册服务
            service.export();
        }
        {
            DemoService2 demoServiceImpl2 = new DemoServiceImpl2();
            ServiceConfig<DemoService2> service = new ServiceConfig(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
            service.setApplication(application);
            service.setRegistry(registry); // 多个注册中心可以用setRegistries()
            service.setProtocol(protocol); // 多个协议可以用setProtocols()
            service.setInterface(DemoService2.class);
            service.setRef(demoServiceImpl2);
            service.setVersion("1.0.0");
            service.setTimeout(2000);
            service.export();
        }
//        new CountDownLatch(1).await();

    }


    public void main2() throws InterruptedException {


// 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("provider-of-app");
        application.setLogger("log4j");

// 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setGroup("dubbo-app");
        registry.setTimeout(50000);
        registry.setAddress("zookeeper://192.168.77.130:2181");
//        registry.setUsername("aaa");
//        registry.setPassword("bbb");

// 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(200);

// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

// 服务提供者暴露服务配置
        {
            // 服务实现
            DemoServiceImpl demoService = new DemoServiceImpl();
            demoService.setConfig(protocol);
            ServiceConfig<DemoService> service = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
            service.setApplication(application);
            service.setRegistry(registry); // 多个注册中心可以用setRegistries()
            service.setProtocol(protocol); // 多个协议可以用setProtocols()
            service.setInterface(DemoService.class);
            service.setRef(demoService);
            service.setVersion("1.0.0");
            service.setTimeout(2000);

// 暴露及注册服务
            service.export();
        }
        {
            DemoService2 demoServiceImpl2 = new DemoServiceImpl2();
            ServiceConfig<DemoService2> service = new ServiceConfig(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
            service.setApplication(application);
            service.setRegistry(registry); // 多个注册中心可以用setRegistries()
            service.setProtocol(protocol); // 多个协议可以用setProtocols()
            service.setInterface(DemoService2.class);
            service.setRef(demoServiceImpl2);
            service.setVersion("1.0.0");
            service.setTimeout(2000);
            service.export();
        }
//        new CountDownLatch(1).await();

    }
}
