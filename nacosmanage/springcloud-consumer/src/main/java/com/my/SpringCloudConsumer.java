package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudConsumer {
    public static void main( String[] args )
    {
        SpringApplication.run(SpringCloudConsumer.class,args);
    }
}
