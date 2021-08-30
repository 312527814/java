package com.my.api;


public class DemoServiceImpl2 implements DemoService2 {

    public String sayHello(String name) {
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hello " + name);
        return "Hello " + name;
    }

    @Override
    public String sayWord(String name) {
        System.out.println("Word  " + name);
        return "Word " + name;
    }


}
