package com.giousa.daily.ftl;

import com.giousa.daily.bean.ColumnData;
import com.giousa.daily.utils.FileUtils;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTLBuilderUtilsV2 {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setDefaultEncoding("utf-8");
        // 设置模版资源目录
        configuration.setClassForTemplateLoading(configuration.getClass(), "/ftl");
        // 加载资源目录下的 ftl 文件，生成模版对象
        Template template = configuration.getTemplate("MybatisDOWithLombok.ftl");
        // 准备输出流，本例中为字符串输出，读者也可使用文件流将填充过的模版输出到文件
        StringWriter stringWriter = new StringWriter();
        // 数据准备，可以用 Map 装载数据，也可以用对象
        Map<String, Object> data = new HashMap<>();
        data.put("Package", "com.giousa.study");
        data.put("TableName", "TeacherRecord");
        List<ColumnData> columnDataList = Lists.newArrayList();
        ColumnData columnData1 = new ColumnData();
        columnData1.setComment("年龄");
        columnData1.setType("Integer");
        columnData1.setColumnCamelName("showName");

        ColumnData columnData2 = new ColumnData();
        columnData2.setComment("姓名");
        columnData2.setType("String");
        columnData2.setColumnCamelName("age");

        ColumnData columnData3 = new ColumnData();
        columnData3.setComment("主键ID");
        columnData3.setType("Long");
        columnData3.setColumnCamelName("id");

        columnDataList.add(columnData1);
        columnDataList.add(columnData2);
        columnDataList.add(columnData3);
        data.put("columnDataList", columnDataList);

        template.process(data, stringWriter);
        String content = stringWriter.toString();

        System.out.println(content);
        System.out.println("模板生成完毕！");

        buildFile(content);

        System.out.println("文件生成完毕！");
    }

    private static void buildFile(String content) {
        FileUtils.writeFile(content,"TeacherRecord.java");
    }

}
