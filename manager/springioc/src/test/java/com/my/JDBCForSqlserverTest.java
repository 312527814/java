package com.my;

import com.my.pojp.Flower;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCForSqlserverTest {
    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext-jdbc-sqlserver.xml");
        DataSource bean = context.getBean(DataSource.class);
        Connection connection = bean.getConnection();
        PreparedStatement preparedStatement1 = connection.prepareStatement("select * from [User] where  id=? or id=?");
        preparedStatement1.setObject(1,1);
        preparedStatement1.setObject(2,2);
        ResultSet resultSet1 = preparedStatement1.executeQuery();

        FlowerMapper flowerMapper = new FlowerMapper();
        ArrayList<Flower> flowers = new ArrayList<>();
        while (resultSet1.next()){
            Flower flower = new Flower();
            flower.setId(resultSet1.getInt("id"));
            flower.setName(resultSet1.getString("Name"));
            flower.setPrice(resultSet1.getDouble("Age"));
            flowers.add(flower);
        }



    }

    public class FlowerMapper  {
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
