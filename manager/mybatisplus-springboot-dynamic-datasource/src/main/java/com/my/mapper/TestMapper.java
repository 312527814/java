package com.my.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.pojo.Test;
import org.apache.ibatis.annotations.Param;

/**
 * @program:
 * @description:
 * @author: liang.liu
 * @create: 2021-11-14 19:12
 */
@DS("master")
public interface TestMapper extends BaseMapper<Test> {


    Test selectByName(@Param("name1") String name);

}