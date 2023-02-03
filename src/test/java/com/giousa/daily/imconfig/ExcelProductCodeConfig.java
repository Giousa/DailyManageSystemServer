package com.giousa.daily.imconfig;

import com.alibaba.excel.EasyExcel;

public class ExcelProductCodeConfig {

    public static void main(String[] args) {
        String filename = "/Users/zhangmengmeng/Documents/stjk_resources/sc和产品码映射关系-0525.xlsx";
        EasyExcel.read(filename, ProductCodesDTO.class,new ProductCodesExcelListener()).sheet().doRead();
    }
}
