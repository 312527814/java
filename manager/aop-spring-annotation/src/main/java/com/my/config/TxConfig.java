package com.my.config;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-07-22 15:41
 */
@Configuration
@ComponentScan(basePackages = {"com.my.servicetx", "com.my.advisor"})
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class TxConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static InfrastructureAdvisorAutoProxyCreator infrastructureAdvisorAutoProxyCreator() {
        return new InfrastructureAdvisorAutoProxyCreator();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/myTest?serverTimezone=GMT%2b8&characterEncoding=utf-8&characterSetResults=utf-8&useUnicode=false&useSSL=false&nullCatalogMeansCurrent=true");
//        ds.setUsername("root");
//        ds.setPassword("123456");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }
}

