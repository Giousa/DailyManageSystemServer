package com.giousa.daily.mapper;

import com.giousa.daily.model.QtWorkRecord;

public interface QtWorkRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QtWorkRecord record);

    int insertSelective(QtWorkRecord record);

    QtWorkRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QtWorkRecord record);

    int updateByPrimaryKey(QtWorkRecord record);
}