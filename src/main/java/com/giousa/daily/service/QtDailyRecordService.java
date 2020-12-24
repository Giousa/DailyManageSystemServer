package com.giousa.daily.service;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.model.QtDailyRecord;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/24
 * Email:65489469@qq.com
 */
public interface QtDailyRecordService {


    ResultVO addQtDailyRecord(QtDailyRecord qtDailyRecord);

    ResultVO findQtDailyRecordListByPage(String userId,String beginDt,String endDt,int page,int size);

    ResultVO deleteQtDailyRecord(Integer id);
}
