package com.my.service;

import com.my.advisor.annotation.MyTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Override
    @MyTransactional
    public int run() {
        System.out.println("run.........");
        return 1;
    }
}
