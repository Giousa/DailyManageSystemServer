package com.giousa.daily.bean.sms;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/11
 * Email:65489469@qq.com
 */
@Data
public class SMSResultBean implements Serializable {

    private String Message;
    private String RequestId;
    private String BizId;
    private String Code;

}
