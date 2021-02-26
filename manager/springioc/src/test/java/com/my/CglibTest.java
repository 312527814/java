package com.my;

import com.my.proxy.cglib.CglibBoss;
import com.my.proxy.cglib.IBoss;
import com.my.proxy.cglib.CglibSecretary;
import org.junit.Test;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CglibTest {
    @Test
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "e:\\class");
        Enhancer enhancer = new Enhancer();
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        CglibSecretary mishu = ac.getBean("cglibSecretary", CglibSecretary.class);
        enhancer.setSuperclass(CglibBoss.class);
        enhancer.setCallback(mishu);

        IBoss boss = (IBoss) enhancer.create();
        boss.Say("dsds");

    }
}
