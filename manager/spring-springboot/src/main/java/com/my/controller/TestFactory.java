package com.my.controller;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-09-28 18:15
 */
@Component
public class TestFactory implements FactoryBean<MyTest> {
    @Override
    public MyTest getObject() throws Exception {
        return new MyTest();
    }

    @Override
    public Class<?> getObjectType() {
        return MyTest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
