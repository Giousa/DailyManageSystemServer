package com.giousa.daily.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColumnData implements Serializable {

    /**
     * 字段名称(驼峰)
     */
    private String columnCamelName;

    /**
     * 字段名称(蛇形)
     */
    private String columnSnakeName;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段注释
     */
    private String comment;

    /**
     * 表数据
     */
    private TableData tableData;
}
