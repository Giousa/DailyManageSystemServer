package com.giousa.daily.mapper;

import com.giousa.daily.model.QtCompany;

import java.util.List;

public interface QtCompanyMapper {
    int deleteByPrimaryKey(String id);

    int insert(QtCompany record);

    int insertSelective(QtCompany record);

    QtCompany selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QtCompany record);

    int updateByPrimaryKeyWithBLOBs(QtCompany record);

    int updateByPrimaryKey(QtCompany record);

    List<QtCompany> findCompanyList();

    void deleteCompany(String id);
}