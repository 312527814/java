package com.my.mapper;

import com.my.pojo.user;

import java.util.List;

public interface userMapper {
    int insert(user record);

    int insertSelective(user record);

    List<user> selectAll();

    user selectById(int id);
}