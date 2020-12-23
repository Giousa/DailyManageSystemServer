package com.giousa.daily.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/10
 * Email:65489469@qq.com
 */
public class KeyUtil {



    /**
     * 生成32位不重复主键
     *
     * @return
     */
    public static synchronized String getKeyId() {

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }


    /**
     * 生成8位随机数字
     * @return
     */
    public static synchronized String getEightRandomCode() {

        String randomStr = RandomStringUtils.random(8, "12345678901234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");

        return randomStr;
    }

    /**
     * 生成6位随机数字
     * @return
     */
    public static synchronized String getSixRandomCode() {

        return (int)((Math.random()*9+1)*100000)+"";
    }

    /**
     * 生成时间戳+随机数
     * @return
     */
    public static synchronized String getTimeCode(){
        Random random = new Random();

        String randomInt = String.valueOf(random.nextInt(90000) + 10000);

        String format = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

        return format+randomInt;

    }

    /**
     * 生成随机字母和数字
     * @return
     */
    public static synchronized String getRandomCode(){

        String randomStr = RandomStringUtils.random(7, "abcdefghijklmnopqrstuvwxyz1234567890");

        String format = new SimpleDateFormat("SSS").format(new Date());

        return format+randomStr;

    }
}
