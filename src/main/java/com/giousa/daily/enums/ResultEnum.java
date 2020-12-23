package com.giousa.daily.enums;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/10
 * Email:65489469@qq.com
 */
public enum ResultEnum {

    SERVE_EXCEPTION(11,"服务器繁忙，请稍后再试"),
    SQL_TOO_MANY_RESULTS(12,"数据库存在异常数据"),
    SQL_ID_HAS_EXIST(13,"当前编号已存在"),

    //系统
    ID_IS_WRONG(103,"编号异常"),
    PARAM_EMPTY(104,"参数不能为空"),


    //手机
    FORMAT_PHONE_WRONG(51,"手机格式不正确"),
    FORMAT_PASSWPRD_WRONG(52,"密码长度不正确"),

    //验证码
    VERIFY_CODE_HAS_SEND(202,"验证码已发送"),
    VERIFY_CODE_SEND_MULTY(203,"短信发送太频繁，请稍后再试"),
    VERIFY_CODE_SEND_FAILURE(204,"短信发送失败"),
    VERIFY_CODE_LOSE(205,"验证码已失效"),
    VERIFY_CODE_ERROR(206,"验证码错误"),


    //文件
    FILE_UPLOAD_FAIL(420,"文件上传失败"),
    FILE_UPLOAD_SUFFIX_FAIL(421,"文件格式有误"),
    FILE_IS_EMPTY(422,"文件不能为空"),


    //用户
    USER_PHONE_HAS_EXIST(1001,"当前手机号已注册"),





    ;






    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
