package com.my.ioc;

public class MyPointcut {
    private  String className;
    private  String methodName;
    public MyPointcut(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}
