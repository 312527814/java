package com.my.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan(basePackages = "com.my",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value ={RestController.class, Controller.class})
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value =WebAppConfig.class)
})
public class FatherConfig {
}
