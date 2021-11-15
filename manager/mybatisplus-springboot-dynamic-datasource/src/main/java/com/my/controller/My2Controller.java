package com.my.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.my.dao.MybatisDao;
import com.my.dao.MybatisDao2;
import com.my.mapper.MyTestMapper;
import com.my.mapper.TestMapper;
import com.my.pojo.MyTest;
import com.my.pojo.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class My2Controller {

    private static final Logger log = LoggerFactory.getLogger(My2Controller.class);

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private MyTestMapper myTestMapper;

    @Autowired
    private MybatisDao mybatisDao;

    @Autowired
    private MybatisDao2 mybatisDao2;


    @GetMapping("my2/index")
    public String index() {
        Test zhangsan1 = testMapper.selectByName("zhangsan1");
        return "hello word";
    }

    @GetMapping("my2/index2")
    public String index2() {
        MyTest zhangsan1 = myTestMapper.selectByName("露西亚1");
        return "hello word";
    }

    @GetMapping("my2/index3")
    public String index3() {
//        Test mybatisDao1 = mybatisDao.get1();
        MyTest mybatisDao2 = mybatisDao.get2();
        return "hello word";
    }

    @DS("slave_1")
    @GetMapping("my2/index4")
    public String index4() {
        Test mybatisDao1 = mybatisDao.get1();
        MyTest mybatisDao2 = mybatisDao.get2();
        return "hello word";
    }

    @DS("slave_1")
    @GetMapping("my2/index5")
    public String index5() {
        Test zhangsan1 = testMapper.selectByName("zhangsan1");
        MyTest zhangsan2 = myTestMapper.selectByName("露西亚1");
        return "hello word";
    }


    @GetMapping("my2/index6")
    public String index6() {
        MyTest zhangsan1 = mybatisDao2.get2();
        return "hello word";
    }


}
