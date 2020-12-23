package com.giousa.daily.service;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.model.QtSort;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
public interface QtSortService {

    ResultVO addQtSort(QtSort qtSort);

    ResultVO updateQtSort(QtSort qtSort);

    ResultVO findQtSortList(String type,String name);

    ResultVO findQtSortInfo(Integer id);

    ResultVO deleteQtSort(Integer id);
}
