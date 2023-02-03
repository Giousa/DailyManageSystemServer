package com.giousa.daily.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColumnData implements Serializable {

    /**
     * 字段注释
     */
    private String comment;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段名称
     */
    private String name;
}
