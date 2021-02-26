package com.my;

import com.my.model.MyDubboProxy;

public class MyDubboClient {
    public static void main(String[] args) throws InterruptedException, NoSuchMethodException {
        MyDubboProxy proxy = new MyDubboProxy();
        DemoService create = proxy.Create(DemoService.class);
        String str = create.say("你好ddad啊");
        System.out.print(str);
    }

}
