package com.giousa.daily.mapper;

import com.giousa.daily.model.QtSort;

public interface QtSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QtSort record);

    int insertSelective(QtSort record);

    QtSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QtSort record);

    int updateByPrimaryKey(QtSort record);
}