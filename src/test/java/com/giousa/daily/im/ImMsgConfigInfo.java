package com.giousa.daily.im;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImMsgConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 业务场景
     */
    private String scene;

    /**
     * 消息类型
     */
    private String imType;

    /**
     * 触发类型(DEFAULT-默认，CONSULTINT-问诊中)
     */
    private String triggerType;

    /**
     * 业务码(DEFAULT-默认)
     */
    private String bizCode;

    /**
     * 消息体
     */
    private String imContent;

    /**
     * 间隔时间(单位分钟)
     */
    private Integer intervalTime;

    /**
     * appid
     */
    private Integer appId;

    /**
     * 渲染渠道
     */
    private String channel;

    /**
     * 跳转平台
     */
    private String platforms;

    /**
     * 路由类型
     */
    private String routeType;

    /**
     * 排序值(按从大到小顺序依次发送)
     */
    private Integer sort;

    /**
     * 扩展信息
     */
    private String extProperty;
}

