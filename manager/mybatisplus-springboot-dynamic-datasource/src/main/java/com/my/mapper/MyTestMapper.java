package com.my.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.pojo.MyTest;
import org.apache.ibatis.annotations.Param;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-11-14 19:12
 */
//@DS("slave_1")
public interface MyTestMapper extends BaseMapper<MyTest> {


    MyTest selectByName(@Param("name1") String name);

}