package com.my;

import com.my.mapper.flowerMapper;
import com.my.pojo.flower;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MyApp {

    @Test
    public void main3() throws IOException {


            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().setDefaultEnumTypeHandler(org.apache.ibatis.type.EnumOrdinalTypeHandler.class);
        {
            SqlSession session = sqlSessionFactory.openSession();
            flowerMapper mapper = session.getMapper(flowerMapper.class);
            flower flower = mapper.selectById(10);

            session.commit();
        }

        {

            SqlSession session = sqlSessionFactory.openSession();
            flowerMapper mapper = session.getMapper(flowerMapper.class);
            flower flower = mapper.selectById(2010);

            session.commit();
        }

        {

            SqlSession session = sqlSessionFactory.openSession();
            flowerMapper mapper = session.getMapper(flowerMapper.class);
            flower flower = mapper.selectById(2011);

            session.commit();
        }


        {

            SqlSession session = sqlSessionFactory.openSession();
            flowerMapper mapper = session.getMapper(flowerMapper.class);
            flower flower = mapper.selectById(2011);

            session.commit();
        }





//        session.commit();

    }


    @Test
    public  void main() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//        DefaultSqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        {
            // ��ʼ��mybatis������SqlSessionFactory���ʵ��

            SqlSession session = sqlSessionFactory.openSession();
            flowerMapper mapper = session.getMapper(flowerMapper.class);

            int offset = 0;
            //连续取出几条数据
            int limit = 5;

            RowBounds rowBounds = new RowBounds(offset, limit);
            List<flower> users = mapper.selectAll(rowBounds);
            session.commit();
        }

    }

}
