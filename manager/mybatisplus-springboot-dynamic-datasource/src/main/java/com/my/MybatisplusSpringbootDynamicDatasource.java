package com.my;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan("com.my.mapper")
public class MybatisplusSpringbootDynamicDatasource
{
    public static void main( String[] args )
    {
        SpringApplication.run(MybatisplusSpringbootDynamicDatasource.class, args);
    }
}
