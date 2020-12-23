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

    private final static String sms_sign = "爱享康";
    private final static String sms_login_template = "SMS_168081355";

    /**
     * 发送短信
     * @param phoneNum
     * @param verifyCode
     * @return 1：成功  2：发送太频繁 3：发送失败
     */
    public static synchronized int sendMsg(String phoneNum, String verifyCode){

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIh6K3QYEOtQMo", "56jYl2vckHmKnduwQt4mAjLPfYNf64");

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", sms_sign);
        request.putQueryParameter("TemplateCode", sms_login_template);

        Map<String, String> param= new HashMap<>();
        param.put("code", verifyCode);
        request.putQueryParameter("TemplateParam", JsonUtils.objectToJson(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println("发送短信："+response.getData());
            SMSResultBean smsResultBean = JSON.parseObject(response.getData(), SMSResultBean.class);
            if("OK".equals(smsResultBean.getCode())){
                //存储验证码，用作注册和修改密码时校验 15分钟
//                CookieUtils.writeCookie(httpServletResponse,"hes_cookie", sixRandomCode);
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
