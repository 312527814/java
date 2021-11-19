package com.my;

import org.junit.Test;

import java.sql.*;
import java.util.concurrent.CountDownLatch;

public class TestJDBC {
    public static void main(String[] args) {
        //定义需要的对象
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        try {

            //2.得到连接(1433表示sql server的默认端口)
            ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=UTC", "root", "123456");
            ct.setAutoCommit(false);
            //3.创建Preparestatement,创建数据
            {
                ps = ct.prepareStatement("insert into a values (88,88)");
                int i = ps.executeUpdate();
                //如果执行的是ddl语句
//                boolean b = ps.execute();
//                if (b) {
//                    System.out.println("创建成功！");
//                } else {
//                    System.out.println("失败");
//                }
            }
            ct.commit();
            {
                ps = ct.prepareStatement("insert into a values (77,77)");
                //如果执行的是ddl语句
                boolean b = ps.execute();
                if (b) {
                    System.out.println("创建成功！");
                } else {
                    System.out.println("失败");
                }
            }
            ct.commit();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executeBatch() throws Exception {
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;

        //2.得到连接(1433表示sql server的默认端口)
        ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=UTC&rewriteBatchedStatements=true", "root", "123456");

        //3.创建Preparestatement,创建数据

        ps = ct.prepareStatement("insert into my_test(name,age) values (?,?)");
        for (int i = 0; i < 10; i++) {
            ps.setString(1, "zhangsan" + i);
            ps.setString(2, i + "");
            ps.addBatch();//添加到同一个批处理中
        }

        int[] ints = ps.executeBatch();//执行批处理
    }

    @Test
    public void selectStream() throws Exception {
        PreparedStatement ps = null;

        Connection ct = null;
        ResultSet rs = null;

        //2.得到连接(1433表示sql server的默认端口)
        ct = DriverManager.getConnection("jdbc:mysql://192.168.77.138:3306/test?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=UTC&rewriteBatchedStatements=true", "root", "123456");

        //3.创建Preparestatement,创建数据

        ps = ct.prepareStatement("select * from card", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        ps.setFetchSize(Integer.MIN_VALUE); //也可以修改jdbc url通过defaultFetchSize参数来设置，这样默认所以的返回结果都是通过流方式读取.
        ResultSet resultSet2 = ps.executeQuery();

        int aa = 0;
        while (resultSet2.next()) {

            Object object = resultSet2.getObject("id");
            int a = 0;
        }

        new CountDownLatch(1).await();
    }


    @Test
    public void selectExecuteQuery() throws Exception {
        PreparedStatement ps = null;

        Connection ct = null;
        ResultSet rs = null;

        //2.得到连接(1433表示sql server的默认端口)
        ct = DriverManager.getConnection("jdbc:mysql://192.168.77.138:3306/test?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&serverTimezone=UTC&rewriteBatchedStatements=true", "root", "123456");

        //3.创建Preparestatement,创建数据

        ps = ct.prepareStatement("select * from card");

        ResultSet resultSet = ps.executeQuery();

        int aa = 0;
        while (resultSet.next()) {
            Object object = resultSet.getObject("id");
            int a = 0;
        }


        new CountDownLatch(1).await();
    }

    public static void main2(String[] args) {
        //定义需要的对象
        PreparedStatement ps = null;
        Connection ct = null;
        ResultSet rs = null;
        try {
            //初始化对象
            //1.加载驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.得到连接(1433表示sql server的默认端口)
            ct = DriverManager.getConnection("jdbc:mysql://172.172.172.238:3306/dentistrydb?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8", "dentistrydb", "den_Ikang0905");
            //3.创建Preparestatement,创建数据
            ps = ct.prepareStatement("insert into dental_check_result\n" +
                    "(orgid,workno,itemindex_miscode,itemindex_name,itemindex_result_value,itemindex_show_index,created_on,updated_on,customer_checkitem_id)\n" +
                    "values(792,0210170314019,'1.7.4.2','牙体','龋齿:18;',72,'2018-09-07 18:18:44',now(),53)");
//			ps=ct.prepareStatement("create table xxx");//创建表
//			ps=ct.prepareStatement("backup database shen to disk='F:/123.bak'");//备份数据库

            //如果执行的是ddl语句
            boolean b = ps.execute();
            int[] ints = ps.executeBatch();
            if (b) {
                System.out.println("创建成功！");
            } else {
                System.out.println("失败");
            }

//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
