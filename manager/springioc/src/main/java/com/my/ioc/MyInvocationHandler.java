package com.my.ioc;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class MyInvocationHandler implements InvocationHandler {
    private MyAspect aspect;
    private Object target;

    public MyInvocationHandler(MyAspect aspect, Object target) {
        this.aspect = aspect;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        MyPointcut pointcut = this.aspect.getPointcut();
        if (target.getClass().getName().contains(pointcut.getClassName()) && method.getName().contains(pointcut.getMethodName())) {
            // return  this.aspect.getAdvice().invoke(target,method,args);
            for (MyBeforeAdvice beforeAdvice : aspect.getAdvice()) {
                beforeAdvice.invoke(target,method,args);
            }
        }
        return method.invoke(target, args);
    }
}
