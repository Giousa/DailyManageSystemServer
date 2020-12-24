package com.giousa.daily.service.impl;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.bean.user.QtUserInfoVO;
import com.giousa.daily.bean.user.QtUserRegisterBody;
import com.giousa.daily.enums.ResultEnum;
import com.giousa.daily.mapper.QtUserMapper;
import com.giousa.daily.model.QtUser;
import com.giousa.daily.service.QtUserService;
import com.giousa.daily.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/23
 * Email:65489469@qq.com
 */
@Service
public class QtUserServiceImpl implements QtUserService {

    @Autowired
    private QtUserMapper qtUserMapper;

    @Override
    public ResultVO getRegisterVerifyCode(String phone) {

        String key = "user_reg_"+phone;
        return sendAlySms(phone, key,"reg");
    }

    @Override
    public ResultVO getLoginVerifyCode(String phone) {

        String key = "user_login_"+phone;
        return sendAlySms(phone, key,"login");
    }

    private ResultVO sendAlySms(String phone,String key,String type){

        if(!VerifyUtils.CheckMobilePhoneNum(phone)){

            return ResultVO.error(ResultEnum.FORMAT_PHONE_WRONG);
        }

        String sixRandomCode = KeyUtil.getSixRandomCode();
        System.out.println("登录验证码：key = "+key+",sixRandomCode = "+sixRandomCode);

        ExpiryMap<String,String> expiryMap = ExpiryMap.getStringInstance();
        String s = expiryMap.get(key);

        if(StringUtil.isEmpty(s)){
            //发送短信
            int i = SMSAliyunUtils.sendMsg(phone, sixRandomCode,type);
            if( i == 1){
                expiryMap.put(key,sixRandomCode,1000*60*15);
                return ResultVO.ok("验证码已经发送至手机，请注意查收！");
            }else if(i == 2){
                return ResultVO.error(ResultEnum.VERIFY_CODE_SEND_MULTY);
            }else {
                return ResultVO.error(ResultEnum.VERIFY_CODE_SEND_FAILURE);
            }

        }else {
            //提醒已发送
            return ResultVO.error(ResultEnum.VERIFY_CODE_HAS_SEND);

        }
    }

    @Override
    public ResultVO registerUser(QtUserRegisterBody qtUserRegisterBody) {

        String name = qtUserRegisterBody.getName();
        String phone = qtUserRegisterBody.getPhone();
        String password = qtUserRegisterBody.getPassword();
        String verifyCode = qtUserRegisterBody.getVerifyCode();
        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(phone) || StringUtil.isEmpty(password) || StringUtil.isEmpty(verifyCode)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        if(!VerifyUtils.CheckMobilePhoneNum(phone)){

            return ResultVO.error(ResultEnum.FORMAT_PHONE_WRONG);
        }

        if(!VerifyUtils.CheckPassword(password)){
            return ResultVO.error(ResultEnum.FORMAT_PASSWPRD_WRONG);
        }

        //验证码检测
        ExpiryMap<String,String> expiryMap = ExpiryMap.getStringInstance();
        String code = expiryMap.get("user_reg_" + phone);
        if(StringUtil.isEmpty(code)){
            return ResultVO.error(ResultEnum.VERIFY_CODE_LOSE);
        }

        if(!code.equals(verifyCode)){
            return ResultVO.error(ResultEnum.VERIFY_CODE_ERROR);
        }

        //判断当前手机号是否已注册
        boolean flag = qtUserMapper.findQtUserExistFlagByPhone(phone);
        if(flag){
            return ResultVO.error(ResultEnum.USER_PHONE_HAS_EXIST);
        }

        //注册
        QtUser qtUser = new QtUser();
        BeanUtils.copyProperties(qtUserRegisterBody,qtUser);

        qtUser.setId(KeyUtil.getKeyId());
        qtUser.setPassword(Md5Util.stringToMd5(password));
        qtUser.setCreateTime(new Date());
        qtUser.setUpdateTime(new Date());

        qtUserMapper.insert(qtUser);

        return ResultVO.ok(qtUser);
    }

    @Override
    public ResultVO loginUserByPassword(String phone, String password) {

        if(!VerifyUtils.CheckMobilePhoneNum(phone)){

            return ResultVO.error(ResultEnum.FORMAT_PHONE_WRONG);
        }

        if(!VerifyUtils.CheckPassword(password)){
            return ResultVO.error(ResultEnum.FORMAT_PASSWPRD_WRONG);
        }

        QtUserInfoVO qtUserInfoVO = qtUserMapper.loginUserByPassword(phone,Md5Util.stringToMd5(password));

        return ResultVO.ok(qtUserInfoVO);
    }

    @Override
    public ResultVO loginUserBySms(String phone, String verifyCode) {

        if(!VerifyUtils.CheckMobilePhoneNum(phone)){

            return ResultVO.error(ResultEnum.FORMAT_PHONE_WRONG);
        }

        //验证码检测
        ExpiryMap<String,String> expiryMap = ExpiryMap.getStringInstance();
        String code = expiryMap.get("user_login_" + phone);
        if(StringUtil.isEmpty(code)){
            return ResultVO.error(ResultEnum.VERIFY_CODE_LOSE);
        }

        if(!code.equals(verifyCode)){
            return ResultVO.error(ResultEnum.VERIFY_CODE_ERROR);
        }

        QtUserInfoVO qtUserInfoVO = qtUserMapper.loginUserBySms(phone);

        return ResultVO.ok(qtUserInfoVO);
    }

    @Override
    public ResultVO addUser(QtUser qtUser) {
        String name = qtUser.getName();
        String phone = qtUser.getPhone();

        if(StringUtil.isEmpty(name) || StringUtil.isEmpty(phone)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }

        if(!VerifyUtils.CheckMobilePhoneNum(phone)){

            return ResultVO.error(ResultEnum.FORMAT_PHONE_WRONG);
        }

        //判断当前手机号是否已注册
        boolean flag = qtUserMapper.findQtUserExistFlagByPhone(phone);
        if(flag){
            return ResultVO.error(ResultEnum.USER_PHONE_HAS_EXIST);
        }

        String password = qtUser.getPassword();
        if(!StringUtil.isEmpty(password)){
            if(!VerifyUtils.CheckPassword(password)){
                return ResultVO.error(ResultEnum.FORMAT_PASSWPRD_WRONG);
            }
            qtUser.setPassword(Md5Util.stringToMd5(password));
        }else {
            qtUser.setPassword(Md5Util.stringToMd5("123456"));
        }

        qtUser.setId(KeyUtil.getKeyId());
        qtUser.setCreateTime(new Date());
        qtUser.setUpdateTime(new Date());

        qtUserMapper.insert(qtUser);

        return ResultVO.ok("添加成功");

    }

    @Override
    public ResultVO updateUser(QtUser qtUser) {

        String id = qtUser.getId();

        if(StringUtil.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_EMPTY);
        }


        QtUser qtUserDB = qtUserMapper.selectByPrimaryKey(id);
        if(qtUserDB == null){
            return ResultVO.error(ResultEnum.ID_IS_WRONG);
        }


        String password = qtUser.getPassword();
        if(!StringUtil.isEmpty(password)){
            if(!VerifyUtils.CheckPassword(password)){
                return ResultVO.error(ResultEnum.FORMAT_PASSWPRD_WRONG);
            }
            qtUser.setPassword(Md5Util.stringToMd5(password));
        }
        qtUser.setUpdateTime(new Date());

        qtUserMapper.updateByPrimaryKeySelective(qtUser);

        return ResultVO.ok("修改成功");
    }

    @Override
    public ResultVO findUserList(String name) {

        List<QtUserInfoVO> list = qtUserMapper.findUserList(DataHandlerUtils.sqlLikeStr(name));
        return ResultVO.ok(list);
    }

    @Override
    public ResultVO findUserInfo(String id) {
        QtUserInfoVO qtUserInfoVO = qtUserMapper.findUserInfo(id);

        return ResultVO.ok(qtUserInfoVO);
    }

    @Override
    public ResultVO deleteUser(String id) {

        QtUser qtUserDB = qtUserMapper.selectByPrimaryKey(id);
        if(qtUserDB == null){
            return ResultVO.error(ResultEnum.ID_IS_WRONG);
        }

        qtUserMapper.deleteByPrimaryKey(id);
        return ResultVO.ok("删除成功");
    }
}
