package com.giousa.daily.mapper;

import com.giousa.daily.model.QtSort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QtSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QtSort record);

    int insertSelective(QtSort record);

    QtSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QtSort record);

    int updateByPrimaryKey(QtSort record);

    List<QtSort> findQtSortList(@Param("type") String type,@Param("name") String name);
}