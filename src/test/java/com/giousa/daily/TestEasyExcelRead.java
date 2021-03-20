package com.giousa.daily;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;

public class TestEasyExcelRead {

    @Test
    public void readTest(){
//        String filename = "/Users/zhangmengmeng/Desktop/杉泰/快捷回复汇总.xlsx";
        String filename = "/Users/zhangmengmeng/Desktop/杉泰/快捷回复-测试环境.xlsx";
        EasyExcel.read(filename, TagWithReplyDTO.class,new ExcelListener()).sheet().doRead();
    }
}
