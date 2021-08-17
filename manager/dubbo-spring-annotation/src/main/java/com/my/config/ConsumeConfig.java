package com.my.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo
@PropertySource("classpath:/spring/dubbo-consume.properties")
@ComponentScan("com.my.serviceconsumer")
public class ConsumeConfig {
}
