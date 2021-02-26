package com.my.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CglibSecretary implements MethodInterceptor {


    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        System.out.println("预约时间");
        //invoke()调用子类重写的方法
//		arg1.invoke(arg0, arg2);
//		arg3.invokeSuper(arg0, arg2);
        Object result = arg3.invokeSuper(arg0, arg2);


        System.out.println("备注");
        return result;
    }

}