package com.my.ioc;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class IocContainer {
    private Map<String, Object> beans = new HashMap<>();

    public void register(Class<?> c) throws IllegalAccessException, InstantiationException {
        String name = c.getSimpleName();
        Object o = c.newInstance();
        this.register(c, o);
    }

    public void register(Class<?> c, Object instance) {
        String name = c.getSimpleName();
        this.register(name, c, instance);
    }

    public void register(String name, Class<?> c, Object instance) {
        beans.put(name, instance);
    }

    public <T> T getBean(Class<T> c) {
        String name = c.getSimpleName();
        Object target = beans.get(name);
        MyAspect aspect = (MyAspect)beans.get(MyAspect.class.getSimpleName());

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(aspect,target);
        Object o1 = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), myInvocationHandler);
        return (T) o1;
        // return (T) o;
    }
}
