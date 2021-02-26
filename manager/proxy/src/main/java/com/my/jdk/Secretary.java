package com.my.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
