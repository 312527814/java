package com.my.advisor.advice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("执行前置通知");
    }
}
