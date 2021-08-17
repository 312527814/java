package com.my.servicetx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyTxServiceImpl implements MyTxService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class,readOnly = true)
    public int insert() {
        String sel = "select * from flower where id=1";
        jdbcTemplate.query(sel,rs->{
            Object object = rs.getObject("id");
            Object object1 = rs.getObject("Name");
        });
//        String sql = "insert into flower(Name,Price,Production,userId) values('债务比22',4343,'defefe','0')";
//        int i = jdbcTemplate.update(sql);

        String updateSql = "update flower set Price=222 where id=1";
        int update = jdbcTemplate.update(updateSql);

//        int a = 1 / 0;
        return 0;

    }
}
