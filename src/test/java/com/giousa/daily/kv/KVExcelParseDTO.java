package com.giousa.daily.kv;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class KVExcelParseDTO implements Serializable {

    //设置excel表头名称
    @ExcelProperty(index = 2)
    private String doctorName;

    @ExcelProperty(index = 3)
    private String doctorId;

    @ExcelProperty(index = 4)
    private String im1;

    @ExcelProperty(index = 5)
    private String im2;

    @ExcelProperty(index = 6)
    private String im3;

    @ExcelProperty(index = 7)
    private String cardTitle1;

    @ExcelProperty(index = 8)
    private String cardContent1;

    @ExcelProperty(index = 9)
    private String cardPic1;

    @ExcelProperty(index = 10)
    private String cardH51;

    @ExcelProperty(index = 11)
    private String cardXCX1;

    @ExcelProperty(index = 12)
    private String cardWX1;

    @ExcelProperty(index = 13)
    private String cardTitle2;

    @ExcelProperty(index = 14)
    private String cardContent2;

    @ExcelProperty(index = 15)
    private String cardPic2;

    @ExcelProperty(index = 16)
    private String cardH52;

    @ExcelProperty(index = 17)
    private String cardXCX2;

    @ExcelProperty(index = 18)
    private String cardWX2;

    @ExcelProperty(index = 19)
    private String pic1;

    @ExcelProperty(index = 20)
    private String pic2;

    @ExcelProperty(index = 21)
    private Integer imSort1;

    @ExcelProperty(index = 22)
    private Integer imSort2;

    @ExcelProperty(index = 23)
    private Integer imSort3;

    @ExcelProperty(index = 24)
    private Integer cardSort1;

    @ExcelProperty(index = 25)
    private Integer cardSort2;

    @ExcelProperty(index = 26)
    private Integer picSort1;

    @ExcelProperty(index = 27)
    private Integer picSort2;

}
