package com.my.advisor.advice;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class AroundAdvice implements Advice, MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("环绕-前置");
        Object proceed = null;
        if (invocation != null) {
            Method method = invocation.getMethod();
            proceed = invocation.proceed();
        }
        System.out.println("环绕-后置");
        return proceed;
    }
}
