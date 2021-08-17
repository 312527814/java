package com.my.ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface MyBeforeAdvice {
    void invoke(Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
}
