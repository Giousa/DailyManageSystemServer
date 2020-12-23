package com.giousa.daily.controller;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.model.QtCompany;
import com.giousa.daily.service.QtCompanyService;
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
@RequestMapping("commpany")
public class QtCompanyController {

    @Autowired
    private QtCompanyService qtCompanyService;

    @PostMapping("addOrUpdateCompany")
    public ResultVO addOrUpdateCompany(@RequestBody QtCompany qtCompany) {
        return qtCompanyService.addOrUpdateCompany(qtCompany);
    }

    @GetMapping("findCompanyList")
    public ResultVO findCompanyList() {
        return qtCompanyService.findCompanyList();
    }

    @GetMapping("findCompanyInfo")
    public ResultVO findCompanyInfo(@RequestParam("id") String id) {

        if(StringUtil.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        return qtCompanyService.findCompanyInfo(id);
    }

    @GetMapping("deleteCompany")
    public ResultVO deleteCompany(@RequestParam("id") String id) {
        if(StringUtil.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }
        return qtCompanyService.deleteCompany(id);
    }
}
