package com.giousa.daily.ftl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FTLBuilderUtils {

    public static void main(String[] args) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setDefaultEncoding("utf-8");
        // 设置模版资源目录
        configuration.setClassForTemplateLoading(configuration.getClass(), "/ftl");
        // 加载资源目录下的 ftl 文件，生成模版对象
        Template template = configuration.getTemplate("test_ftl.ftl");
        // 准备输出流，本例中为字符串输出，读者也可使用文件流将填充过的模版输出到文件
        StringWriter stringWriter = new StringWriter();
        // 数据准备，可以用 Map 装载数据，也可以用对象
        Map<String, String> data = new HashMap<>();
        data.put("category", "测试");
        data.put("level", "2");
        data.put("remark", "不就是看看");

        template.process(data, stringWriter);
        String content = stringWriter.toString();

        System.out.println(content);
        System.out.println("模板生成完毕！");

        buildFile(content);

        System.out.println("文件生成完毕！");
    }

    private static void buildFile(String content) {
        FileUtils.writeFile(content);
    }

}
