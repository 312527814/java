package com.my.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleApplicationEventMulticasterBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SimpleApplicationEventMulticaster) {
            Executor executor = new ThreadPoolExecutor(1, 5,
                    1000L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(20));

            System.out.print("AsyncConfigurerImpl \r\n");
            //return executor;
            ((SimpleApplicationEventMulticaster) bean).setTaskExecutor(executor);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
