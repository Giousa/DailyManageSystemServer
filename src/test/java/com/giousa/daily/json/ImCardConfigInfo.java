package com.giousa.daily.json;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImCardConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * H5跳转地址，IM消息里actionH5字段
     */
    private String h5Url;
    /**
     * native跳转地址。IM消息里action字段
     */
    private String nativeUrl;
    /**
     * 小程序跳转地址。IM消息里actionXcx字段
     */
    private String xcxUrl;
    /**
     * 医生端跳转地址。IM消息里actionDoctor字段
     */
    private String workbenchUrl;
}
