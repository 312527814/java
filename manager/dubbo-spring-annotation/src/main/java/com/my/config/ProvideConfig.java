package com.my.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "com.my.serviceprovider")
@PropertySource("classpath:/spring/dubbo-provider.properties")
public class ProvideConfig {
}
