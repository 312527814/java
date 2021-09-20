package com.my.config;

import com.my.pojo.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    public Test getTest() {
        Test test = new Test();
        test.setId(11);
        return test;
    }
}
