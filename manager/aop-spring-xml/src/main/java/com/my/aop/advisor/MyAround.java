package com.my.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("环绕-前置");
        Object proceed = methodInvocation.proceed();
        System.out.println("环绕-后置");
        return proceed;


    }
}
