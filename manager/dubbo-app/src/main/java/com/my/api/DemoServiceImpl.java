package com.my.api;



public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
    }


}
