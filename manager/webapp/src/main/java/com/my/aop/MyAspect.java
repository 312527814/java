package com.my.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MyAspect {
    @Pointcut("execution(* com.my.service..*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void methodBefore(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + methodName + "】之前执行<前置通知>，入参" + Arrays.asList(joinPoint.getArgs()));

    }

    @After(value = "pointCut()")
    public void methodAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + methodName + "】之后执行<后置通知>，入参" + Arrays.asList(joinPoint.getArgs()));
    }

    public void aa(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法【" + methodName + "】之后执行<后置通知>，入参" + Arrays.asList(joinPoint.getArgs()));
    }

    public void methodReturning(JoinPoint joinPoint) {
    }

    public void methodAfterThrowing(JoinPoint joinPoint) {
    }
}
