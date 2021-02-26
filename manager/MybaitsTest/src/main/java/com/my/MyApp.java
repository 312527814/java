package com.my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.mapper.flowerMapper;
import com.my.mapper.userMapper;
import com.my.pojo.*;
import com.sun.java.accessibility.util.Translator;
import com.sun.nio.zipfs.JarFileSystemProvider;
import com.sun.nio.zipfs.ZipDirectoryStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.accessibility.Accessible;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;


public class MyApp {
    public static void main3(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//        DefaultSqlSessionFactory

        // ��ʼ��mybatis������SqlSessionFactory���ʵ��
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().setDefaultEnumTypeHandler(org.apache.ibatis.type.EnumOrdinalTypeHandler.class);
        SqlSession session = sqlSessionFactory.openSession();
        userMapper mapper = session.getMapper(userMapper.class);
        List<user> users = mapper.selectAll();


        session.commit();

    }

    public static void sss() throws IOException {
        user stu = new user();
        stu.setAge(1);
        stu.setCreatetime(new Date());
        stu.setId(1);
        stu.setName("张三");


    }

    private Object object = new Object();

    public void ss() {

//        String s="1234ab!十";
        String s = "一";
        byte[] bytes = s.getBytes();
        String s1 = new String(bytes);

        synchronized (object) { // 修改处
            try {
                object.wait(20000);
                System.out.print("打印");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main2(String[] args) {
//        MyApp myApp = new MyApp();
//        myApp.ss();

        try {
            sss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public static void main34(String[] args) throws Exception {

//        Thread.currentThread().setContextClassLoader(MyApp.class.getClassLoader().getParent());
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver", true, MyApp.class.getClassLoader().getParent());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?useUnicode=true&characeterEncoding=utf-8&serverTimezone=UTC", "root", "123456");

//        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");

        //2.得到连接(1433表示sql server的默认端口)
        //3.创建Preparestatement,创建数据

        PreparedStatement statement = connection.prepareStatement("select * from flower where id=?");
////			ps=ct.prepareStatement("create table xxx");//创建表
////			ps=ct.prepareStatement("backup database shen to disk='F:/123.bak'");//备份数据库
//        //如果执行的是ddl语句
        statement.setObject(1, 10);
        statement.execute();


        ResultSet resultSet = statement.getResultSet();
//        ResultSet resultSet = statement.executeQuery();
//
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");

            System.out.print(id);
        }


    }

    public static void main77(String[] args) throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//        DefaultSqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        {
            // ��ʼ��mybatis������SqlSessionFactory���ʵ��

            SqlSession session = sqlSessionFactory.openSession();
            flowerMapper mapper = session.getMapper(flowerMapper.class);
            List<flower> users = mapper.selectOrderBy(2011, "id");
            session.commit();
        }

        {
            SqlSession session = sqlSessionFactory.openSession();
            flowerMapper mapper = session.getMapper(flowerMapper.class);
            List<flower> users = mapper.selectOrderBy(2011, "id");
            session.commit();
        }




    }


    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?useUnicode=true&characeterEncoding=utf-8&serverTimezone=UTC", "root", "123456");
        Statement statement = connection.createStatement();
        PreparedStatement statement1 = connection.prepareStatement("");
        statement.executeQuery("select * from flower where id=1");


        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");

            System.out.print(id);
        }


    }
}
