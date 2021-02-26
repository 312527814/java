package com.my.interceptor;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.IntegerTypeHandler;

import java.sql.SQLException;
import java.util.*;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class ExecutePlugin implements Interceptor {
    private Properties properties = new Properties();
    public Object intercept(Invocation invocation) throws Throwable {
        // implement pre processing if need

        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        MapperMethod.ParamMap<Object> parameterObject = (MapperMethod.ParamMap<Object>) args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        BoundSql boundSql = ms.getBoundSql(parameterObject);

        String sql = boundSql.getSql();
        parameterObject.put("first", 1);
        parameterObject.put("end", 10);

        Object returnObject=  pageQuery(executor, ms, parameterObject, rowBounds, resultHandler, boundSql);
        return returnObject;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static <E> List<E> pageQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {

        MapperMethod.ParamMap<Object> parameterObject = (MapperMethod.ParamMap<Object>) parameter;
        parameterObject.put("first", 0);
        parameterObject.put("end", 10);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        ParameterMapping.Builder builder = new ParameterMapping.Builder(ms.getConfiguration(),"first", new IntegerTypeHandler()).javaType(int.class);
        ParameterMapping first = builder.build();
        parameterMappings.add(first);
        ParameterMapping end = new ParameterMapping.Builder(ms.getConfiguration(),"end", new IntegerTypeHandler()).javaType(int.class).build();
        parameterMappings.add(end);
        String pageSql = "select * from flower where id=? limit ?,?";boundSql.getSql();
        BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
        parameterObject.forEach((f, t) -> {
            System.out.print(f);
            Object additionalParameter = boundSql.getAdditionalParameter(f);
            if(additionalParameter!=null){
                pageBoundSql.setAdditionalParameter(f, additionalParameter);
            }

        });
        CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, pageBoundSql);
    }
}
