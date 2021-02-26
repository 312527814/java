package com.my.ioc;

import java.util.List;

public class MyAspect {

    private MyPointcut pointcut;
    private List<MyBeforeAdvice> beforeAdvices;

    public MyAspect(MyPointcut pointcut, List<MyBeforeAdvice> beforeAdvices) {
        this.pointcut = pointcut;
        this.beforeAdvices = beforeAdvices;
    }

    public MyPointcut getPointcut() {
        return pointcut;
    }

    public List<MyBeforeAdvice> getAdvice() {
        return beforeAdvices;
    }
}
