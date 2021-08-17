package com.my.advisor;

import com.my.advisor.advice.AroundAdvice;
import com.my.advisor.advice.BeforeAdvice;
import com.my.advisor.annotation.MyTransactional;
import com.my.service.MyService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MyPointcutAdvisor implements Advisor, PointcutAdvisor {


    @Override
    public Pointcut getPointcut() {
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return clazz -> {
                    Class<?>[] interfaces = clazz.getInterfaces();
                    if (interfaces != null) {
                        for (Class<?> anInterface : interfaces) {
                            if (anInterface == MyService.class) {
                                return true;
                            }
                        }
                    }
                    return false;
                };
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                MethodMatcher methodMatcher = new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        Method[] methods = targetClass.getMethods();
                        for (Method targetMethod : methods) {
                            if(method.getName().equals(targetMethod.getName())){
                                MyTransactional annotation = targetMethod.getAnnotation(MyTransactional.class);
                                if (annotation != null) {
                                    return true;
                                }
                            }
                        }

                        return false;
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
                return methodMatcher;
            }
        };
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return new AroundAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
