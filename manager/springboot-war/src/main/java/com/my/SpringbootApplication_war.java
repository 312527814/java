package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Hello world!
 */
@SpringBootApplication
public class SpringbootApplication_war extends SpringBootServletInitializer {
//    public static void main(String[] args) {
//        SpringApplication.run(SpringbootApplication_war.class, args);
//    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(SpringbootApplication_war.class);
    }
}
