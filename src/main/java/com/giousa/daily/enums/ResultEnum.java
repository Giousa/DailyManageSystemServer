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

    ID_IS_WRONG(103,"编号异常"),
    PARAM_EMPTY(104,"参数不能为空"),

    FILE_UPLOAD_FAIL(420,"文件上传失败"),
    FILE_UPLOAD_SUFFIX_FAIL(421,"文件格式有误"),
    FILE_IS_EMPTY(422,"文件不能为空"),


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
