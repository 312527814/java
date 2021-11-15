package com.my.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-11-14 01:05
 */
@Component
public class MyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public String get1(){
        String s = jdbcTemplate.queryForObject("select count(1) from test", String.class);
        return s;
    }

    @DS("slave_1")
    public String get2(){
        String s = jdbcTemplate.queryForObject("select count(1) from my_test", String.class);
        return s;
    }
}
