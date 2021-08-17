package com.my.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {
    public void before() {
        System.out.println("执行前置通知 before");
    }
    public void after() {
        System.out.println("执行后置通知 before");
    }

    public  Object arround(ProceedingJoinPoint p) throws Throwable {
        System.out.println("环绕前置");
        Object proceed = p.proceed();
        System.out.println("环绕后置");
        return proceed;
    }

}
