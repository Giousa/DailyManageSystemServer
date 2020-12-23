package com.giousa.daily.mapper;

import com.giousa.daily.model.QtUser;

public interface QtUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(QtUser record);

    int insertSelective(QtUser record);

    QtUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QtUser record);

    int updateByPrimaryKey(QtUser record);
}