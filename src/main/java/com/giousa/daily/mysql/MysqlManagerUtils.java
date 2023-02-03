package com.giousa.daily.mysql;

import com.alibaba.fastjson.JSON;
import com.giousa.daily.bean.ColumnData;
import com.giousa.daily.bean.TableData;
import com.giousa.daily.utils.FTLUtils;
import com.giousa.daily.utils.JdbcUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class MysqlManagerUtils {

    /**
     * 表Map
     */
    private static Map<String, TableData> tableMap = Maps.newHashMap();

    /**
     * 字段Map
     */
    private static Map<String,List<ColumnData>> columnMap = Maps.newHashMap();

    public static void main(String[] args) throws Exception{
        Connection conn = JdbcUtils.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();
        //获取所有表
        ResultSet rs = metaData.getTables(conn.getCatalog(), null, null, null);
        while (rs.next()) {
            String tableSnakeName = rs.getString("TABLE_NAME"); //表名

            TableData tableData = new TableData();
            tableData.setTableSnakeName(tableSnakeName);
            tableData.setTableUpperCamelName(FTLUtils.snakeToUpperCamel(tableSnakeName));
            tableData.setTableCamelName(FTLUtils.snakeToCamel(tableSnakeName));
            tableMap.put(tableSnakeName,tableData);

            List<ColumnData> list = Lists.newArrayList();

            ResultSet rsTable = conn.getMetaData().getColumns(null, null, tableSnakeName,"%");
            while(rsTable.next()){
//                System.out.println("表名："+rsTable.getString("TABLE_NAME")+",字段名："+rsTable.getString("COLUMN_NAME")+",字段类型："+rsTable.getString("TYPE_NAME")+",字段注释："+rsTable.getString("REMARKS"));
                String column_name = rsTable.getString("COLUMN_NAME");
                String type_name = rsTable.getString("TYPE_NAME");
                String remarks = rsTable.getString("REMARKS");

                ColumnData columnData = new ColumnData();
                columnData.setColumnCamelName(FTLUtils.snakeToCamel(column_name));
                columnData.setColumnSnakeName(column_name);
                columnData.setType(FTLUtils.sqlToType(type_name));
                columnData.setComment(remarks);

                TableData tableData2 = new TableData();
                tableData2.setTableSnakeName(tableSnakeName);
                tableData2.setTableUpperCamelName(FTLUtils.snakeToUpperCamel(tableSnakeName));
                tableData2.setTableCamelName(FTLUtils.snakeToCamel(tableSnakeName));
                columnData.setTableData(tableData2);
                list.add(columnData);
            }

            columnMap.put(tableSnakeName,list);
        }

        System.out.println("数据初始化完毕！");
        System.out.println("表数据："+ JSON.toJSONString(tableMap));
        System.out.println("字段数据："+JSON.toJSONString(columnMap));

        createSpringCodeFile();
    }

    private static final String basePackageName = "com.giousa.diary";

    private static void createSpringCodeFile() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        list.parallelStream().forEach(it -> {
            switch (it){
                case 1:
                    createDO();
                    break;
                case 2:
                    createDTO();
                    break;
                case 3:
                    createMapper();
                    break;
                case 4:
                    createXML();
                    break;
                case 5:
                    createManager();
                    break;
                case 6:
                    createController();
                    break;
                case 7:
                    createConvert();
                    break;
                case 8:
                    createPageQueryRequest();
                    break;
                case 9:
                    createRequest();
                    break;
                case 10:
                    createJSON();
                    break;
            }
        });
//        createDO();
//        createDTO();
//        createMapper();
//        createXML();
//        createManager();
//        createController();
//        createConvert();
//        createPageQueryRequest();
//        createRequest();
//        createJSON();
    }

    private static void createDO() {
        String packageName = basePackageName+".core.model";
    }

    private static void createDTO() {
    }

    private static void createMapper() {
    }

    private static void createXML() {
    }

    private static void createManager() {
    }

    private static void createController() {
    }

    private static void createConvert() {

    }

    private static void createPageQueryRequest() {

    }

    private static void createRequest() {

    }

    private static void createJSON() {

    }
}
