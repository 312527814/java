package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Hello world!
 *
 */
//@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ZuulApp.class, args);
    }
}
