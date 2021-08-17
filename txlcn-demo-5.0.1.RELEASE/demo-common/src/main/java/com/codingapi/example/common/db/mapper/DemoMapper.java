package com.codingapi.example.common.db.mapper;

import com.codingapi.example.common.db.domain.Demo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Mapper
public interface DemoMapper {

    @Insert("insert into t_demo_d(kid, demo_field, group_id, unit_id, create_time,app_name) values(#{kid}, #{demoField}, #{groupId}, #{unitId}, #{createTime},#{appName})")
    void save(Demo demo);

    @Delete("delete from t_demo_d where id=#{id}")
    void deleteByKId(Long id);


    @Delete("update t_demo_d set demo_field=#{demoField} where id=#{id}")
    void update(Demo demo);
}
