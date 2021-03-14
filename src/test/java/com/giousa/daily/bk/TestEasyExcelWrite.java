package com.giousa.daily.bk;


import com.alibaba.excel.EasyExcel;
import com.giousa.daily.TagWithReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("dev")
public class TestEasyExcelWrite {

    @Test
    public void test1(){

        //实现excel写的操作
        //1 设置写入文件夹地址和excel文件名称
        String filename = "/Users/zhangmengmeng/Desktop/杉泰/快捷回复汇总.xlsx";
        // 2 调用easyexcel里面的方法实现写操作
        // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename, TagWithReplyDTO.class).sheet("学生列表").doWrite(getData());
    }

    //创建方法返回list集合
    private List<TagWithReplyDTO> getData() {
        List<TagWithReplyDTO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            TagWithReplyDTO data = new TagWithReplyDTO();
//            data.setSno(i);
//            data.setSname("lucy"+i);
//            list.add(data);
        }
        return list;
    }
}
