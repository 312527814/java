package com.my.advisor;

import com.my.advisor.advice.AfterAdvice;
import com.my.advisor.advice.BeforeAdvice;
import com.my.service.MyService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.stereotype.Component;

@Component
public class MyIntroductionAdvisor implements Advisor, IntroductionAdvisor {
    private Advice advice;

    @Override
    public Advice getAdvice() {
        advice = new BeforeAdvice();// AfterAdvice(); //new AroundAdvice();
        return advice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

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
    public void validateInterfaces() throws IllegalArgumentException {

    }

    @Override
    public Class<?>[] getInterfaces() {
        return new Class[0];
    }


}
