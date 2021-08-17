package com.my;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@MapperScan("com.my.mapper")
@SpringBootApplication
public class MybatisSpringboot
{
    public static void main( String[] args )
    {

        SpringApplication.run(MybatisSpringboot.class, args);
    }
}
