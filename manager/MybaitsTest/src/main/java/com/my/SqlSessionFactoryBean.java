package com.my;

import com.my.mapper.flowerMapper;
import com.my.pojo.flower;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;


public class SqlSessionFactoryBean {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config2.xml");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        SqlSessionFactory factory = sqlSessionFactoryBean.build(inputStream);
        SqlSession session = factory.openSession();


        flowerMapper mapper = session.getMapper(flowerMapper.class);
        String string = mapper.toString();
        flower flower = mapper.selectById(2010);
//        List<com.my.pojo.flower> flowers = mapper.selectAll();
//
//        System.out.print(flower);
    }

    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    String[] paths;
    SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactoryBean() {

        paths = new String[]{"H:/javalianxi/manager/MybaitsTest/src/main/resources/mapper/flowerMapper.xml"};
    }

    public SqlSessionFactory build(InputStream inputStream) throws IOException {

//        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, null, null);
//        Configuration configuration = parser.parse();
//        DataSource  dataSource =new Dat
//      configuration.setEnvironment(new Environment());
//        com.mysql.cj.jdbc.Driver
//        TransactionFactory txFactory = this.transactionManagerElement(child.evalNode("transactionManager"));
//        DataSourceFactory dsFactory =DataSourceFactory this.dataSourceElement(child.evalNode("dataSource"));
//        DataSource dataSource = dsFactory.getDataSource();
//        Environment.Builder environmentBuilder = (new Environment.Builder(id)).transactionFactory(txFactory).dataSource(dataSource);
//        this.configuration.setEnvironment(environmentBuilder.build());
        TransactionFactory transactionFactory=new JdbcTransactionFactory();
        DataSource dataSource=new PooledDataSource("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/mytest?useUnicode=true&characeterEncoding=utf-8&serverTimezone=UTC","root","123456");

        Configuration configuration = new Configuration();
        Environment environment=new Environment("1",transactionFactory,dataSource);
        configuration.setEnvironment(environment);
        for (String path : paths) {
            File file = new File(path);
            InputStream stream = Files.newInputStream(file.toPath());
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(stream, configuration, path, configuration.getSqlFragments());
            xmlMapperBuilder.parse();
        }
        return sqlSessionFactoryBuilder.build(configuration);
    }


}
