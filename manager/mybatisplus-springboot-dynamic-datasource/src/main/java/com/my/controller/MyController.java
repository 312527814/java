package com.my.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.my.dao.MyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MyController {
    @Autowired
   private JdbcTemplate jdbcTemplate;

    @Autowired
    private MyDao dao;
    @Resource
    Environment environment;

    @GetMapping("index")
    public String index() {
        String s = jdbcTemplate.queryForObject("select count(1) from test", String.class);

        return  "hello word";
    }
    @DS("slave_1")
    @GetMapping("index2")
    public String index2() {
        String s = jdbcTemplate.queryForObject("select count(1) from my_test", String.class);

        return  "hello word";
    }

    @GetMapping("index3")
    public String index3() {
        String dao1 = dao.get1();
        String dao2 = dao.get2();


        return  "hello word";
    }






}
