package com.my.api;


import com.alibaba.dubbo.config.ProtocolConfig;

public class DemoServiceImpl implements DemoService {

    private ProtocolConfig config;

    public void setConfig(ProtocolConfig config) {
        this.config = config;
    }

    public String sayHello(String name) {
//        try {
//            Thread.sleep(1000 * 2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Integer port = 0;
        if (config != null) {
            port = config.getPort();
        }

        System.out.println("Hello " + name + " prot:" + port);
        return "Hello " + name + " port:" + port;
    }


}
