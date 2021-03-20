package com.giousa.daily.publictag;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PTagWithReplyDTO implements Serializable {

    //设置excel表头名称
    @ExcelProperty(value = "科室名",index = 0)
    private String name;

    @ExcelProperty(value = "科室id",index = 1)
    private Long deptId;

    @ExcelProperty(value = "标签名",index = 2)
    private String tagName;

    @ExcelProperty(value = "快捷回复",index = 3)
    private String  replyContent;
}
