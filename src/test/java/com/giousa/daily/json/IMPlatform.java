package com.giousa.daily.json;

import lombok.ToString;

@ToString
public enum  IMPlatform {

    NATIVE("native", "客户端"),
    H5("h5", "h5"),
    WORKBENCH("doctor", "工作台"),
    WX_XCX("xcx", "微信小程序");


    private String code;
    private String desc;

    IMPlatform(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
