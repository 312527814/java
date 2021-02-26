package com.my;

import com.my.ioc.*;
import com.my.servlet.BaseServlet;
import com.my.servlet.MyServlet;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyIocTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IocContainer ioc = new IocContainer();
        MyPointcut pointcut=new MyPointcut("MyServlet","run");
        MyBeforeAdvice advice=new testBeforeAdvice1();
        List<MyBeforeAdvice> advices=new ArrayList<>();
        advices.add(new testBeforeAdvice1());
        advices.add(new testBeforeAdvice2());
        MyAspect myAspect=new MyAspect(pointcut,advices);

        ioc.register(MyAspect.class,myAspect);
        ioc.register(BaseServlet.class, new MyServlet());

        BaseServlet bean = ioc.getBean(BaseServlet.class);

        bean.run();
    }
    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IocContainer ioc = new IocContainer();
        MyPointcut pointcut=new MyPointcut("MyServlet","run");
        MyBeforeAdvice advice=new testBeforeAdvice1();
        List<MyBeforeAdvice> advices=new ArrayList<>();
        advices.add(new testBeforeAdvice1());
        advices.add(new testBeforeAdvice2());
        MyAspect myAspect=new MyAspect(pointcut,advices);

        ioc.register(MyAspect.class,myAspect);
        ioc.register(BaseServlet.class, new MyServlet());

        BaseServlet bean = ioc.getBean(BaseServlet.class);

        bean.run();
    }

    public static class  testBeforeAdvice1 implements MyBeforeAdvice {

        @Override
        public void invoke(Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            System.out.println("执行前1");
        }
    }

    public static class testBeforeAdvice2 implements MyBeforeAdvice {

        @Override
        public void invoke(Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            System.out.println("执行前2");
        }
    }
}



