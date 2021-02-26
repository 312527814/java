package com.my;

import com.my.controller.MyController;
import com.my.ioc.IocContainer;
import com.my.proxy.jdk.Boss;
import com.my.proxy.jdk.IBoss;
import com.my.proxy.jdk.Secretary;
import com.my.servlet.BaseServlet;
import com.my.servlet.MyServlet;
import com.my.servlet.MyServlet3;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AopTest {
    @Value("${a}")
    private  String name;

    @Test
    public void test() throws InterruptedException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyServlet3 myServlet = ac.getBean(MyServlet3.class);
        myServlet.testAop();

       // System.out.println(name);

//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-Arround.xml");
//        MyServlet3 bean = ac.getBean(MyServlet3.class);
//
//
//        bean.testAop();


    }


    @Test
    public void test2() throws InterruptedException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Secretary mishu = new Secretary();
        mishu.setO(new Boss());
        //第一个参数:反射时使用的类加载器
        //第二个参数:Proxy需要实现什么接口
        //第三个参数:通过接口对象调用方法时,需要调用哪个类的invoke方法
        IBoss boss = (IBoss) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{IBoss.class}, mishu);
        System.out.println(boss.getClass());
        boss.Do("我是你大哥");


    }


}


