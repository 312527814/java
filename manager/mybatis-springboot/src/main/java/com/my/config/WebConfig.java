package com.my.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${spring.datasource.driverClassName}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.maxActive}")
    private int maxActiveConnections;
    @Value("${spring.datasource.maxIdle}")
    private int maxIdleConnections;

    @Bean
    public DataSource creatDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(driver);
        pooledDataSource.setUrl(url);
        pooledDataSource.setUsername(userName);
        pooledDataSource.setPassword(password);
        pooledDataSource.setPoolMaximumActiveConnections(maxActiveConnections);
        pooledDataSource.setPoolMaximumIdleConnections(maxIdleConnections);
        return  pooledDataSource;
    }
}
