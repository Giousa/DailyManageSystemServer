package com.giousa.daily.imconfig;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductCodesDTO implements Serializable {

    //设置excel表头名称
    @ExcelProperty(value = "SC",index = 3)
    private String serviceCode;

    @ExcelProperty(value = "对应产品码1",index = 5)
    private String productCode1;




}
