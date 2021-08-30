package com.my.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibSecretary implements MethodInterceptor {

    private  Object targe;

    public void setO(Object o) {
        this.targe = o;
    }
    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        System.out.println("预约时间");
        //invoke()调用子类重写的方法
//		arg1.invoke(arg0, arg2);
//		arg3.invokeSuper(arg0, arg2);
//        Object result = arg3.invokeSuper(arg0, arg2);
//        Object result = arg1.invoke(o, arg2);
        Object result = arg3.invoke(targe, arg2);
        System.out.println("备注");
        return result;
    }

}