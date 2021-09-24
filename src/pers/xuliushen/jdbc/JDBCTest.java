package pers.xuliushen.jdbc;

import java.sql.*;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-08-16 10:02
 * @Modified by :
 */
public class JDBCTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pps = null;
        ResultSet resultset = null;
        int result = 0;
        try {
            //1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得连接
            String username = "root";
            String password = "123456";
            String url = "jdbc:mysql://localhost:3306/kaikeba?serverTimezone=UTC";
            connection = DriverManager.getConnection(url,username,password);
            //3.定义sql创建预状态通道（进行sql的发送)
            String sql = "select * from employee where name=? and title=?";
            pps = connection.prepareStatement(sql);
            pps.setString(1,"李四");
            pps.setString(2,"工程师");
            resultset = pps.executeQuery();
            //4.取出结果集信息
            while(resultset.next()){
                //取出数据
                String name = resultset.getString("name");
                String sex = resultset.getString("sex");
                System.out.println("姓名："+name+",性别："+sex);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if(resultset != null) {
                    //关闭数据库连接
                    resultset.close();
                }
                if(pps != null){
                    pps.close();
                }
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
