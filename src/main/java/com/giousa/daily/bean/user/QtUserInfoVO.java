package com.giousa.daily.bean.user;

import com.giousa.daily.bean.comp.QtCompanyEmployeUserVO;
import com.giousa.daily.model.QtCompany;
import com.giousa.daily.model.QtSort;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@Data
public class QtUserInfoVO implements Serializable {

    private String id;

    private String name;

    private String nickname;

    private String phone;

    private String address;

    private String headImg;

    private Integer gender;

    private String email;

    private String sign;

    private Date createTime;

    private Date updateTime;

    private QtCompany companyInfo;

    private QtCompanyEmployeUserVO employeInfo;

    private QtSort sortInfo;
}
