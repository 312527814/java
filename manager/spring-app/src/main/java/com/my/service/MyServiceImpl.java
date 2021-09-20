package com.my.service;

import com.my.pojo.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Flower> fun() {
        List<Flower> query = jdbcTemplate.query("select * from flower where  id=? or id=?", preparedStatement -> {
            preparedStatement.setObject(1, 2010);
            preparedStatement.setObject(2, 2011);
        }, (rs, i) -> {
            Flower flower = new Flower();
            flower.setId(rs.getInt("id"));
            flower.setName(rs.getString("name"));
            flower.setPrice(rs.getDouble("price"));
            flower.setProduction(rs.getString("production"));
            return flower;
        });

        return query;
    }

    public int insert() throws Exception {
        String sql = "insert into flower(Name,Price,Production,userId) values('债务比',4343,'defefe','0')";
        int i = jdbcTemplate.update(sql);
//        if (true) {
//            throw new Exception("");
//        }
        return i;

    }
}
