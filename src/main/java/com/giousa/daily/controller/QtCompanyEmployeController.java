package com.giousa.daily.controller;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.bean.comp.QtCompanyEmployeBody;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.service.QtCompanyEmployeService;
import com.giousa.daily.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("employe")
public class QtCompanyEmployeController {

    @Autowired
    private QtCompanyEmployeService qtCompanyEmployeService;

    @PostMapping("addOrUpdateQtCompanyEmploye")
    public ResultVO addOrUpdateQtCompanyEmploye(@RequestBody QtCompanyEmployeBody qtCompanyEmployeBody) {
        return qtCompanyEmployeService.addOrUpdateQtCompanyEmploye(qtCompanyEmployeBody);
    }

    @GetMapping("findQtCompanyEmployeList")
    public ResultVO findQtCompanyEmployeList(@RequestParam(value = "companyId",required = false)  String companyId,
                                             @RequestParam(value = "levelId",required = false) Integer levelId,
                                             @RequestParam(value = "page",required = false,defaultValue = "1") int page,
                                             @RequestParam(value = "size",required = false,defaultValue = "10") int size) {
        return qtCompanyEmployeService.findQtCompanyEmployeList(companyId, levelId, page, size);
    }

    @GetMapping("findQtCompanyEmployeInfo")
    public ResultVO findQtCompanyEmployeInfo(@RequestParam("id") String id) {
        if(StringUtil.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }
        return qtCompanyEmployeService.findQtCompanyEmployeInfo(id);
    }

    @GetMapping("deleteQtCompanyEmploye")
    public ResultVO deleteQtCompanyEmploye(@RequestParam("id") String id) {
        if(StringUtil.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }
        return qtCompanyEmployeService.deleteQtCompanyEmploye(id);
    }
}
