package com.giousa.daily.bean.comp;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@Data
public class QtCompanyEmployeBody implements Serializable {

    private String id;

    private String companyId;

    private String userId;

    private Integer levelId;

    private String entryDt;

    private String quitDt;
}
