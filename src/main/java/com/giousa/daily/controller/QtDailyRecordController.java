package com.giousa.daily.controller;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.model.QtDailyRecord;
import com.giousa.daily.service.QtDailyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/24
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("daily-record")
public class QtDailyRecordController {

    @Autowired
    private QtDailyRecordService qtDailyRecordService;

    @PostMapping("addQtDailyRecord")
    public ResultVO addQtDailyRecord(@RequestBody QtDailyRecord qtDailyRecord) {
        return qtDailyRecordService.addQtDailyRecord(qtDailyRecord);
    }

    @GetMapping("findQtDailyRecordListByPage")
    public ResultVO findQtDailyRecordListByPage(@RequestParam(value = "userId",required = false) String userId,
                                                @RequestParam(value = "beginDt",required = false) String beginDt,
                                                @RequestParam(value = "endDt",required = false) String endDt,
                                                @RequestParam(value = "page",required = false,defaultValue = "1") int page,
                                                @RequestParam(value = "size",required = false,defaultValue = "10") int size) {
        return qtDailyRecordService.findQtDailyRecordListByPage(userId,beginDt,endDt,page,size);
    }

    @GetMapping("deleteQtDailyRecord")
    public ResultVO deleteQtDailyRecord(@RequestParam("id") Integer id) {
        if(id == null){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }
        return qtDailyRecordService.deleteQtDailyRecord(id);
    }
}
