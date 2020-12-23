package com.giousa.daily.service;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.bean.comp.QtCompanyEmployeBody;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
public interface QtCompanyEmployeService {

    ResultVO addOrUpdateQtCompanyEmploye(QtCompanyEmployeBody qtCompanyEmployeBody);

    ResultVO findQtCompanyEmployeList(String companyId,Integer levelId,int page,int size);

    ResultVO findQtCompanyEmployeInfo(String id);

    ResultVO deleteQtCompanyEmploye(String id);
}
