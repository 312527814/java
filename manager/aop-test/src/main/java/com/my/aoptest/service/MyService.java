package com.my.aoptest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MyService implements IMyService {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    @Transactional
    public String alive() {
//        List<Object> query = jdbcTemplate.query("select * from a", new RowMapper<Object>() {
//            @Override
//            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
//                return null;
//            }
//        });
//        int update = jdbcTemplate.update("update a set name=2 where id=1");

//        int a = 1 / 0;
        return "hello word";
    }
}
