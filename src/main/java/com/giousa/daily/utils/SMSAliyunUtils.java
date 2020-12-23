package com.giousa.daily.utils;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.giousa.daily.bean.sms.SMSResultBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/11
 * Email:65489469@qq.com
 */
public class SMSAliyunUtils {

    private final static String sms_sign = "Daily";
    //注册验证码
    private final static String sms_reg_template = "SMS_177251728";

    //登录验证码
    private final static String sms_login_template = "SMS_177251950";

    /**
     * 发送短信
     * @param phoneNum
     * @param verifyCode
     * @return 1：成功  2：发送太频繁 3：发送失败
     */
    public static synchronized int sendMsg(String phoneNum, String verifyCode,String type){

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fz1MuhtPas3VWyXnrX2", "NwDZNDWaDGS4eDu8EemwFpiDzcBGlV");

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", sms_sign);

        if("login".equals(type)){
            request.putQueryParameter("TemplateCode", sms_login_template);
        }else if("reg".equals(type)){
            request.putQueryParameter("TemplateCode", sms_reg_template);
        }else {
            return 3;
        }

        Map<String, String> param= new HashMap<>();
        param.put("code", verifyCode);
        request.putQueryParameter("TemplateParam", JsonUtils.objectToJson(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("发送短信："+response.getData());
            SMSResultBean smsResultBean = JSON.parseObject(response.getData(), SMSResultBean.class);
            if("OK".equals(smsResultBean.getCode())){
                //存储验证码，用作注册和修改密码时校验 15分钟
                System.out.println("短信发送成功");
                return 1;
            }else {
                if("isv.BUSINESS_LIMIT_CONTROL".equals(smsResultBean.getCode())){
                    System.out.println("短信发送太频繁，请稍后再试");
                    return 2;
                }else {
                    System.out.println("短信发送失败");
                    return 3;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }

    }

}
