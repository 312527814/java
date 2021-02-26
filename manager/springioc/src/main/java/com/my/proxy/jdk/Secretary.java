package com.my.proxy.jdk;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
public class Secretary implements InvocationHandler {
    private  Object o;

    public void setO(Object o) {
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(o, args);
        return result;

    }


}
