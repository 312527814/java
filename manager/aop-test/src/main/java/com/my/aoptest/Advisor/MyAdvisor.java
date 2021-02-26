package com.my.aoptest.Advisor;

import com.my.aoptest.advice.YouAdvice;
import com.my.aoptest.service.MyService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.stereotype.Component;

@Component
public class MyAdvisor implements Advisor, IntroductionAdvisor {
    private Advice advice;

    @Override
    public Advice getAdvice() {
        advice = new YouAdvice();
        return advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            if (clazz == MyService.class) {
                return true;
            }
            return false;
        };
    }

    @Override
    public void validateInterfaces() throws IllegalArgumentException {

    }

    @Override
    public Class<?>[] getInterfaces() {
        return new Class[0];
    }


}
