package com.giousa.daily.mapper;

import com.giousa.daily.model.QtDailyRecord;

public interface QtDailyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QtDailyRecord record);

    int insertSelective(QtDailyRecord record);

    QtDailyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QtDailyRecord record);

    int updateByPrimaryKey(QtDailyRecord record);
}