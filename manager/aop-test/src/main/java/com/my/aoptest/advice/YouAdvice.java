package com.my.aoptest.advice;

import com.you.YouTest;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class YouAdvice implements Advice, MethodInterceptor {
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
