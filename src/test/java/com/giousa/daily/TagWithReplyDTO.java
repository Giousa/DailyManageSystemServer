package com.giousa.daily;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TagWithReplyDTO implements Serializable {

    //设置excel表头名称
    @ExcelProperty(value = "助手名",index = 0)
    private String name;

    @ExcelProperty(value = "助手id",index = 1)
    private Long userId;

    @ExcelProperty(value = "标签名",index = 2)
    private String tagName;

    @ExcelProperty(value = "快捷回复",index = 3)
    private String  replyContent;


}
