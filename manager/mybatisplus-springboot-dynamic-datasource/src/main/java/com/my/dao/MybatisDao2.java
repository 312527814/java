package com.my.dao;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.my.pojo.MyTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-11-14 01:05
 */
@Component
public class MybatisDao2 {

    @Autowired
    private MybatisDao mybatisDao;

    @DS("slave_1")
    public MyTest get2(){
        MyTest mybatisDao2 = mybatisDao.get3();
        return mybatisDao2;
    }
}
