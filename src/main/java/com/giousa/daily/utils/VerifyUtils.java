package com.giousa.daily.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:校验工具类
 * Author:zhangmengmeng
 * Date:2019/7/11
 * Email:65489469@qq.com
 */
public class VerifyUtils {

    /**
     * 	添加手机号码验证
     * @param phoneNum
     * @return
     */

    public static boolean CheckMobilePhoneNum(String phoneNum) {
        String regex = "^(1[3-9]\\d{9}$)";
        if (phoneNum.length() == 11) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNum);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }

    public static boolean CheckPassword(String password) {

        if(password.length() >= 6 && password.length() <= 16){
            return true;
        }
        return false;
    }


    public static boolean CheckVideoFile(String suffix) {
        String videos = "mp4|flv|avi|rm|rmvb|wmv|mpeg|mov|dat|3gp|dmv";
        if(videos.contains(suffix)){
            return true;
        }else {
            return false;
        }
    }

    public static boolean CheckPicFile(String suffix) {

        String videos = "jpg|png|bmp|jpeg|gif|raw|tiff|webp";
        if(videos.contains(suffix)){
            return true;
        }else {
            return false;
        }
    }

    public static boolean CheckStringValue(String value){
        return value.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
    }

}
