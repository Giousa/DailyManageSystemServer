package com.giousa.daily.im;

import com.alibaba.excel.EasyExcel;

public class ImMsgConfigExcel {

    public static void main(String[] args) {
//        String filename = "/Users/zhangmengmeng/Downloads/workbench_im_msg/2023_01_11_17_22_52_202301111711347206.xlsx";
//        String filename = "/Users/zhangmengmeng/Downloads/workbench_im_msg/2023_01_11_17_22_56_202301111712291246.xlsx";
//        String filename = "/Users/zhangmengmeng/Downloads/workbench_im_msg/2023_01_11_17_23_00_202301111712402570.xlsx";
        String filename = "/Users/zhangmengmeng/Downloads/workbench_im_msg/2023_01_11_17_23_06_202301111712087156.xlsx";

        EasyExcel.read(filename, ImMsgConfigDTO.class,new ImMsgExcelListener()).sheet().doRead();



    }
}
