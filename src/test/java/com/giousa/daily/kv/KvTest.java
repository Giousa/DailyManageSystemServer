package com.giousa.daily.kv;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;

public class KvTest {

    @Test
    public void test1(){

        String filename = "/Users/zhangmengmeng/Desktop/ExcelFiles/副本医生开场白.xlsx";
        EasyExcel.read(filename, KVExcelParseDTO.class,new KVExcelListener()).sheet("Sheet1").doRead();

    }
}
