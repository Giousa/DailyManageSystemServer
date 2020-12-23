package com.giousa.daily.bean.comp;

import com.giousa.daily.model.QtCompany;
import com.giousa.daily.model.QtSort;
import com.giousa.daily.model.QtUser;
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
public class QtCompanyEmployeVO implements Serializable {

    private String id;

    private String companyId;

    private String userId;

    private Integer levelId;

    private Date entryDt;

    private Date quitDt;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private QtCompany companyInfo;

    private QtUser userInfo;

    private QtSort sortInfo;
}
