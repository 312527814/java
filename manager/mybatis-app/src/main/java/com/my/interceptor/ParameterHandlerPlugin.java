package com.my.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;

import java.sql.PreparedStatement;
import java.util.Properties;

@Intercepts({@Signature(
        type = ParameterHandler.class,
        method = "setParameters",
        args = {PreparedStatement.class})})
public class ParameterHandlerPlugin implements Interceptor {
    private Properties properties = new Properties();

    public Object intercept(Invocation invocation) throws Throwable {
        // implement pre processing if need
        Object returnObject = invocation.proceed();
        // implement post processing if need
        return returnObject;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
