package com.giousa.daily.mapper;

import com.giousa.daily.bean.comp.QtCompanyEmployeVO;
import com.giousa.daily.model.QtCompanyEmploye;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QtCompanyEmployeMapper {
    int deleteByPrimaryKey(String id);

    int insert(QtCompanyEmploye record);

    int insertSelective(QtCompanyEmploye record);

    QtCompanyEmploye selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QtCompanyEmploye record);

    int updateByPrimaryKey(QtCompanyEmploye record);

    QtCompanyEmployeVO findQtCompanyEmployeInfo(String id);

    List<QtCompanyEmployeVO> findQtCompanyEmployeList(@Param("companyId") String companyId,
                                                      @Param("levelId") Integer levelId,
                                                      @Param("start") int start,
                                                      @Param("size") int size);

    int findQtCompanyEmployeListTotalCount(@Param("companyId") String companyId,
                                           @Param("levelId") Integer levelId);
}