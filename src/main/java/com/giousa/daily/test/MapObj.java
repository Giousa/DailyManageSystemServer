package com.giousa.daily.test;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2021/2/19
 */
public class MapObj implements Serializable {

    public MapObj(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "MapObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
