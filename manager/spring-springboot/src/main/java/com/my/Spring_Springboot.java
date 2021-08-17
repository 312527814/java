package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Spring_Springboot
{
    public static void main( String[] args )
    {
        SpringApplication.run(Spring_Springboot.class, args);
    }
}
