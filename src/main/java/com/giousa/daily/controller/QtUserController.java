package com.giousa.daily.controller;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.bean.user.QtUserRegisterBody;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.model.QtUser;
import com.giousa.daily.service.QtUserService;
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
@RequestMapping("user")
public class QtUserController {

    @Autowired
    private QtUserService qtUserService;

    @PostMapping("getRegisterVerifyCode")
    public ResultVO getRegisterVerifyCode(@RequestParam("phone")String phone) {
        if(StringUtil.isEmpty(phone)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        return qtUserService.getRegisterVerifyCode(phone);
    }


    @PostMapping("getLoginVerifyCode")
    public ResultVO getLoginVerifyCode(@RequestParam("phone")String phone) {
        if(StringUtil.isEmpty(phone)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        return qtUserService.getLoginVerifyCode(phone);
    }


    @PostMapping("registerUser")
    public ResultVO registerUser(@RequestBody QtUserRegisterBody qtUserRegisterBody) {
        return qtUserService.registerUser(qtUserRegisterBody);
    }

    @PostMapping("loginUserByPassword")
    public ResultVO loginUserByPassword(@RequestParam("phone")String phone,
                                        @RequestParam("password")String password) {

        if(StringUtil.isEmpty(phone) || StringUtil.isEmpty(password)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        return qtUserService.loginUserByPassword(phone,password);
    }

    @PostMapping("loginUserBySms")
    public ResultVO loginUserBySms(@RequestParam("phone")String phone,
                                   @RequestParam("verifyCode")String verifyCode) {

        if(StringUtil.isEmpty(phone) || StringUtil.isEmpty(verifyCode)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        return qtUserService.loginUserBySms(phone,verifyCode);
    }

    @PostMapping("addUser")
    public ResultVO addUser(@RequestBody QtUser qtUser) {
        return qtUserService.addUser(qtUser);
    }

    @PostMapping("updateUser")
    public ResultVO updateUser(@RequestBody QtUser qtUser) {
        return qtUserService.updateUser(qtUser);
    }

    @GetMapping("findUserList")
    public ResultVO findUserList(@RequestParam(value = "name",required = false) String name) {
        return qtUserService.findUserList(name);
    }


    @GetMapping("findUserInfo")
    public ResultVO findUserInfo(@RequestParam("id")String id) {
        if(StringUtil.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        return qtUserService.findUserInfo(id);
    }

    @GetMapping("deleteUser")
    public ResultVO deleteUser(@RequestParam("id")String id) {
        if(StringUtil.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        return qtUserService.deleteUser(id);
    }
}
