package com.giousa.daily.mysql;

import com.alibaba.fastjson.JSON;
import com.giousa.daily.bean.ColumnData;
import com.giousa.daily.bean.TableData;
import com.giousa.daily.utils.FTLUtils;
import com.giousa.daily.utils.FileUtils;
import com.giousa.daily.utils.JdbcUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
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
                columnData.setColumnType(FTLUtils.sqlToType(type_name));
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

        createSpringCodeFile(tableMap,columnMap);
    }

    private static final String basePackageName = "com.giousa.diary";

    private static void createSpringCodeFile(Map<String, TableData> tableMap,Map<String,List<ColumnData>> columnMap) {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        tableMap.forEach((v1,v2) -> {
            list.parallelStream().forEach(it -> {
                switch (it){
                    case 1:
                        createDO(v1);
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

    private static String filePath = "/Users/zhangmengmeng/Desktop/FTL_File/";

    private static void createDO(String tableSnakeName) {
        try {
            String packageName = basePackageName+".core.model";
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            configuration.setDefaultEncoding("utf-8");
            configuration.setClassForTemplateLoading(configuration.getClass(), "/ftl");
            Template template = configuration.getTemplate("DO_template.ftl");
            StringWriter stringWriter = new StringWriter();

            TableData tableData = tableMap.get(tableSnakeName);
            Map<String, Object> data = new HashMap<>();
            data.put("packageName", packageName);
            data.put("tableUpperCamelName", tableData.getTableUpperCamelName());
            List<ColumnData> columnDataList = columnMap.get(tableSnakeName);
            System.out.println("columnDataList: tableSnakeName = "+tableSnakeName);

            data.put("columnDataList", columnDataList);
            template.process(data, stringWriter);
            String content = stringWriter.toString();
            FileUtils.writeFile(filePath+tableData.getTableUpperCamelName()+".java",content);
            System.out.println("DO文件生成完毕！");
        }catch (Exception e){
            System.out.println("DO文件生成失败！"+tableSnakeName);
        }
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
