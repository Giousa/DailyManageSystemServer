package com.giousa.daily.service;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.bean.user.QtUserRegisterBody;
import com.giousa.daily.model.QtUser;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
public interface QtUserService {

    ResultVO getRegisterVerifyCode(String phone);

    ResultVO getLoginVerifyCode(String phone);

    ResultVO registerUser(QtUserRegisterBody qtUserRegisterBody);

    ResultVO loginUserByPassword(String phone,String password);

    ResultVO loginUserBySms(String phone,String verifyCode);

    ResultVO addUser(QtUser qtUser);

    ResultVO updateUser(QtUser qtUser);

    ResultVO findUserList(String name);

    ResultVO findUserInfo(String id);

    ResultVO deleteUser(String id);
}
