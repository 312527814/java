package com.my;

import static org.junit.Assert.assertTrue;

import com.my.proxy.jdk.IBoss;
import com.my.proxy.jdk.Secretary;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testJDKProxy() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        Secretary mishu = ac.getBean("secretary", Secretary.class);
        IBoss boss = ac.getBean("boss", IBoss.class);
        mishu.setO(boss);

        IBoss proxy1 = (IBoss) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{IBoss.class}, mishu);
        System.out.println(proxy1.getClass());
        proxy1.Do("您好大哥");

        IBoss proxy2 = (IBoss) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{IBoss.class}, (proxy, method, args) -> {
            return "";
        });


        proxy2.Do("您好大哥");


        Object o = Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{IBoss.class}, new MyProxy());


    }


    class MyProxy implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return new MyProxy();
        }
    }


}
