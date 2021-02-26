package com.rpc;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MyContext implements Serializable {

    private String interfaceName;
    private String methodName;
    private Object[] args;
    private Class<?>[] argTypes;


    public MyContext(String interfaceName, String methodName, Object[] args, Class<?>[] argTypes) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.args = args;
        this.argTypes = argTypes;
    }


    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?>[] getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(Class<?>[] argTypes) {
        this.argTypes = argTypes;
    }


    @Override
    public String toString() {
        return "MyContext{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", argTypes=" + Arrays.toString(argTypes) +
                '}';
    }
}
