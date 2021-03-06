package com.giousa.daily.mapper;

import com.giousa.daily.model.QtDailyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QtDailyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QtDailyRecord record);

    int insertSelective(QtDailyRecord record);

    QtDailyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QtDailyRecord record);

    int updateByPrimaryKey(QtDailyRecord record);

    List<QtDailyRecord> findQtDailyRecordListByPage(@Param("userId") String userId,
                                                    @Param("beginDt") String beginDt,
                                                    @Param("endDt") String endDt,
                                                    @Param("start") int start,
                                                    @Param("size") int size);

    int findQtDailyRecordListByPageTotalCount(@Param("userId") String userId,
                                              @Param("beginDt") String beginDt,
                                              @Param("endDt") String endDt);
}