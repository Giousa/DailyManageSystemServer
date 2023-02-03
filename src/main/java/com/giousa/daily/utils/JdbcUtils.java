package com.giousa.daily.utils;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    //MySQL版本为8.0+
//    private static String url="jdbc:mysql://139.224.46.106:3306/daily_record?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT";
    //若低于8.0则
    private static String url="jdbc:mysql://139.224.46.106:3306/daily_record?autoReconnect=true&useSSL=false&characterEncoding=utf-8";
    private static String user="root";
    private static String password="h5s/X_7FLkzj";
    //    连接
    public static Connection getConnection() throws Exception {
        //建立连接，MySQL版本为8.0+为com.mysql.cj.jdbc.Driver
        DriverManager.registerDriver(new Driver());
        //若低于8.0则为com.mysql.jdbc.Driver
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
    //    关闭
    public static void close(Statement stmt,Connection con){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
