package com.giousa.daily.mysql;

import com.giousa.daily.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class MysqlManagerUtils {

    public static void main(String[] args) throws Exception{
        Connection conn = JdbcUtils.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println(conn.getCatalog());
        //数据库类型
        System.out.println(metaData.getDatabaseProductName());
        //数据库版本号
        System.out.println(metaData.getDatabaseProductVersion());
        //数据库大版本
        System.out.println(metaData.getDatabaseMajorVersion());
        //jdbc连接的url
        System.out.println(metaData.getURL());
        //获取所有表
        ResultSet rs = metaData.getTables(conn.getCatalog(), null, null, null);
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME"); //表名
            ResultSet rsTable = conn.getMetaData().getColumns(null, null, tableName,"%");
            while(rsTable.next()){
//                System.out.println("TABLE_NAME" +rsTable.getString("TABLE_NAME"));
//                System.out.println("COLUMN_NAME :" +rsTable.getString("COLUMN_NAME"));
//                System.out.println("TYPE_NAME :" +rsTable.getString("TYPE_NAME"));
//                System.out.println("REMARKS :" +rsTable.getString("REMARKS"));
//                System.out.println("\n");
                System.out.println("表名："+rsTable.getString("TABLE_NAME")+",字段名："+rsTable.getString("COLUMN_NAME")+",字段类型："+rsTable.getString("TYPE_NAME")+",字段注释："+rsTable.getString("REMARKS"));
            }
        }


    }
}
