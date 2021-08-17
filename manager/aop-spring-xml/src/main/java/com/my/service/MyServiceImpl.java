package com.my.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int run(){
        String sql = "insert into flower(Name,Price,Production,userId) values('债务比2',4343,'defefe','0')";
        int i = jdbcTemplate.update(sql);
        return i;

    }
}
