package com.my.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//@Component
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        if (beanName.equals("flower")) {
            Class<?>[] interfaces = bean.getClass().getInterfaces();
            if(interfaces.length>0) {
                return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(bean, args);
                    }
                });
            }else{
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(bean.getClass());
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return  method.invoke(o,objects);
                    }
                });

                return enhancer.create();
            }
        }
        return bean;
    }
}
