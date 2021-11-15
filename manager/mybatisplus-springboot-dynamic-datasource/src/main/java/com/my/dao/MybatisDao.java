package com.my.dao;//package com.my.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.my.mapper.MyTestMapper;
import com.my.mapper.TestMapper;
import com.my.pojo.MyTest;
import com.my.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-11-14 01:05
 */
@Component
public class MybatisDao {
    @Autowired
    private TestMapper testMapper;

    @Autowired
    private MyTestMapper myTestMapper;

    @DS("master")
    public Test get1(){
        Test zhangsan1 = testMapper.selectByName("zhangsan1");
        return zhangsan1;
    }

    @DS("slave_1")
    public MyTest get2(){
        MyTest zhangsan1 = myTestMapper.selectByName("露西亚1");
        return zhangsan1;
    }

    public MyTest get3(){
        MyTest zhangsan1 = myTestMapper.selectByName("露西亚1");
        return zhangsan1;
    }
}
