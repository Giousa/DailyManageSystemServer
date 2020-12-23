package com.giousa.daily.service;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.model.QtCompany;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
public interface QtCompanyService {

    ResultVO addOrUpdateCompany(QtCompany qtCompany);

    ResultVO findCompanyList();

    ResultVO findCompanyInfo(String id);

    ResultVO deleteCompany(String id);
}
