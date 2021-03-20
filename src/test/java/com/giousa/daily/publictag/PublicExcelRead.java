package com.giousa.daily.publictag;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;

public class PublicExcelRead {

    @Test
    public void readTest(){
        String filename = "/Users/zhangmengmeng/Desktop/杉泰/公共-快捷回复标签和内容.xlsx";
        EasyExcel.read(filename, PTagWithReplyDTO.class,new PublicExcelListener()).sheet().doRead();
    }
}
