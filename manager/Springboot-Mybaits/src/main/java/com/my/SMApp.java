package com.my;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@MapperScan("com.my.mapper")
@SpringBootApplication
public class SMApp
{
    public static void main( String[] args )
    {

        SpringApplication.run(SMApp.class, args);
    }
}
