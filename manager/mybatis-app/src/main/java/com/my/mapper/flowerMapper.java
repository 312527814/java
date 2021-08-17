package com.my.mapper;

import com.my.pojo.flower;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface flowerMapper {
    flower selectById(@Param("id") int id);

    List<flower> selectAll(RowBounds rowBounds);
    List<flower> selectOrderBy(@Param("id") int id, @Param("orderby") String orderby);

    int updateById(int id);

    int insert(flower record);

    int insertSelective(flower record);
}