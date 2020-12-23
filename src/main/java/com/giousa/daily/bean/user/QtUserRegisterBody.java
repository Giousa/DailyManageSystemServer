package com.giousa.daily.bean.user;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@Data
public class QtUserRegisterBody implements Serializable {

    private String name;

    private String phone;

    private String password;

    private String verifyCode;
}
