package com.my.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    @Around("pointCut()")
    public Object methodAround(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        System.out.println("执行目标方法【" + methodName + "】之前执行<环绕前通知>，入参" + Arrays.asList(pjp.getArgs()));
        Object result = pjp.proceed();
        System.out.println("执行目标方法【" + methodName + "】之前执行<环绕后通知>，入参" + Arrays.asList(pjp.getArgs()));
        return  result;
    }

    @AfterReturning(returning="rvt",value = "pointCut()")
    public void afterReturning(JoinPoint joinPoint,Object rvt) {
        System.out.println("[Aspect1] afterReturning advise");
    }

    public void methodReturning(JoinPoint joinPoint) {
    }

    public void methodAfterThrowing(JoinPoint joinPoint) {
    }
}
