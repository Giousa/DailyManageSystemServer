package com.giousa.daily.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableData implements Serializable {

    /**
     * 表名(首字母大写)
     */
    private String tableUpperCamelName;

    /**
     * 表名(驼峰)
     */
    private String tableCamelName;

    /**
     * 表名(蛇形)
     */
    private String tableSnakeName;
}
