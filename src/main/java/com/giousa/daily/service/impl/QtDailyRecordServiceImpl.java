package com.giousa.daily.service.impl;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.mapper.QtDailyRecordMapper;
import com.giousa.daily.model.QtDailyRecord;
import com.giousa.daily.service.QtDailyRecordService;
import com.giousa.daily.utils.DataHandlerUtils;
import com.giousa.daily.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/24
 * Email:65489469@qq.com
 */
@Service
public class QtDailyRecordServiceImpl implements QtDailyRecordService {

    @Autowired
    private QtDailyRecordMapper qtDailyRecordMapper;

    @Override
    public ResultVO addQtDailyRecord(QtDailyRecord qtDailyRecord) {

        String userId = qtDailyRecord.getUserId();

        String content = qtDailyRecord.getContent();

        if(StringUtil.isEmpty(userId) || StringUtil.isEmpty(content)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        qtDailyRecord.setCreateTime(new Date());

        qtDailyRecordMapper.insert(qtDailyRecord);

        return ResultVO.ok("添加成功");
    }

    @Override
    public ResultVO findQtDailyRecordListByPage(String userId,String beginDt, String endDt, int page, int size) {

        if(!StringUtil.isEmpty(beginDt)){
            beginDt += " 00:00:00";
        }

        if(!StringUtil.isEmpty(endDt)){
            endDt += " 23:59:59";
        }

        List<QtDailyRecord> list = qtDailyRecordMapper.findQtDailyRecordListByPage(userId,beginDt,endDt,(page-1)*size,size);
        int totalCount = qtDailyRecordMapper.findQtDailyRecordListByPageTotalCount(userId,beginDt,endDt);


        return ResultVO.ok(DataHandlerUtils.handlePage(list,page,size,totalCount));
    }

    @Override
    public ResultVO deleteQtDailyRecord(Integer id) {


        QtDailyRecord qtDailyRecord = qtDailyRecordMapper.selectByPrimaryKey(id);
        if(qtDailyRecord == null){
            return ResultVO.error(ResultEnum.ID_IS_WRONG);
        }

        qtDailyRecordMapper.deleteByPrimaryKey(id);

        return ResultVO.ok("删除成功");
    }
}
