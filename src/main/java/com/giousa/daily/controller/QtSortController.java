package com.giousa.daily.controller;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.model.QtSort;
import com.giousa.daily.service.QtSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("sort")
public class QtSortController {

    @Autowired
    private QtSortService qtSortService;

    @PostMapping("addQtSort")
    public ResultVO addQtSort(@RequestBody QtSort qtSort) {
        return qtSortService.addQtSort(qtSort);
    }

    @PostMapping("updateQtSort")
    public ResultVO updateQtSort(@RequestBody QtSort qtSort) {
        return qtSortService.updateQtSort(qtSort);
    }

    @GetMapping("findQtSortList")
    public ResultVO findQtSortList(@RequestParam(value = "type",required = false) String type,
                                   @RequestParam(value = "name",required = false) String name) {
        return qtSortService.findQtSortList(type,name);
    }

    @GetMapping("findQtSortInfo")
    public ResultVO findQtSortInfo(@RequestParam("id") Integer id) {
        if(id == null){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }
        return qtSortService.findQtSortInfo(id);
    }

    @GetMapping("deleteQtSort")
    public ResultVO deleteQtSort(@RequestParam("id") Integer id) {
        if(id == null){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }
        return qtSortService.deleteQtSort(id);
    }
}
