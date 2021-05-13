package com.giousa.daily.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2021/2/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private String id;

    private String name;

    private Integer age;
}
