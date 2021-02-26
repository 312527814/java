package com.my;

import com.my.pojp.Flower;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest {
    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
            System.out.println(beanDefinitionNames[i]);
        }

        DataSource dataSource = context.getBean(DataSource.class);

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement1 = connection.prepareStatement("select * from flower where  id=? or id=?");
        preparedStatement1.setObject(1, 2010);
        preparedStatement1.setObject(2, 2011);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        FlowerMapper flowerMapper = new FlowerMapper();
        ArrayList<Flower> flowers = new ArrayList<>();
        while (resultSet1.next()) {
            Flower flower = new Flower();
            flower.setId(resultSet1.getInt("id"));
            flower.setName(resultSet1.getString("name"));
            flower.setPrice(resultSet1.getDouble("price"));
            flower.setProduction(resultSet1.getString("production"));
            flowers.add(flower);
        }

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Flower> query = jdbcTemplate.query("select * from flower where  id=? or id=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setObject(1, 2010);
                preparedStatement.setObject(2, 2011);
            }
        }, new FlowerMapper());
    }


    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");


        DataSource dataSource = context.getBean(DataSource.class);

        Connection connection = dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        PreparedStatement preparedStatement1 = connection.prepareStatement("select * from flower where  id=? or id=?");
        preparedStatement1.setObject(1, 2010);
        preparedStatement1.setObject(2, 2011);
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        FlowerMapper flowerMapper = new FlowerMapper();
        ArrayList<Flower> flowers = new ArrayList<>();
        while (resultSet1.next()) {
            Flower flower = new Flower();
            flower.setId(resultSet1.getInt("id"));
            flower.setName(resultSet1.getString("name"));
            flower.setPrice(resultSet1.getDouble("price"));
            flower.setProduction(resultSet1.getString("production"));
            flowers.add(flower);
        }


    }

    @Test
    public void test3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext-jdbc.xml");


        DataSource dataSource = context.getBean(DataSource.class);

        Connection connection = dataSource.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        PreparedStatement preparedStatement1 = connection.prepareStatement("update my_user set name='uuuuds' where id=?");
        preparedStatement1.setObject(1, 1000011);
        boolean resultSet1 = preparedStatement1.execute();

    }

    public class FlowerMapper implements RowMapper<Flower> {

        @Override
        public Flower mapRow(ResultSet rs, int i) throws SQLException {

            Flower flower = new Flower();
            flower.setId(rs.getInt("id"));
            flower.setName(rs.getString("name"));
            flower.setPrice(rs.getDouble("price"));
            flower.setProduction(rs.getString("production"));
            return flower;

        }
    }
}
