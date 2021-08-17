package com.my.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyTransService implements IMyTransService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int insert(){
        String sql = "insert into flower(Name,Price,Production,userId) values('债务比2',4343,'defefe','0')";
        int i = jdbcTemplate.update(sql);
        return i;

    }
}
