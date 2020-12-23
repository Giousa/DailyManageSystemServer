package com.giousa.daily.mapper;

import com.giousa.daily.model.QtCompanyEmploye;

public interface QtCompanyEmployeMapper {
    int deleteByPrimaryKey(String id);

    int insert(QtCompanyEmploye record);

    int insertSelective(QtCompanyEmploye record);

    QtCompanyEmploye selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QtCompanyEmploye record);

    int updateByPrimaryKey(QtCompanyEmploye record);
}