package com.my.config;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-07-22 14:04
 */
@Configuration
@ComponentScan(basePackages = {"com.my.service","com.my.advisor"})
public class AdvisorConfig {

    @Bean
    public static AnnotationAwareAspectJAutoProxyCreator aspectJAwareAdvisorAutoProxyCreator(){
        return  new AnnotationAwareAspectJAutoProxyCreator();
    }
}
