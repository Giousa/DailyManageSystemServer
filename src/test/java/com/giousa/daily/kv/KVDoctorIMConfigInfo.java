package com.giousa.daily.kv;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class KVDoctorIMConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 话术配置
     */
    private List<ImMessageConfig> imMessageConfig;

    /**
     * 卡片配置
     */
    private List<ImCardConfig> imCardConfig;

    /**
     * 图片配置
     */
    private List<ImImageConfig> imImageConfig;

    @Data
    @ToString
    public static class ImMessageConfig implements Serializable{

        private static final long serialVersionUID = 1L;


        /**
         * 接收消息平台配置
         */
        private ImFeatureConfig imFeatureConfig;


        /**
         * 话术
         */
        private String message;

        /**
         * 排序
         */
        private Integer sort;
    }

    @Data
    @ToString
    public static class ImImageConfig implements Serializable{

        private static final long serialVersionUID = 1L;


        /**
         * 接收消息平台配置
         */
        private ImFeatureConfig imFeatureConfig;


        /**
         * 图片
         */
        private String image;

        /**
         * 排序
         */
        private Integer sort;
    }



    @Data
    @ToString
    public static class ImCardConfig implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 排序
         */
        private Integer sort;

        /**
         * 是否自动跳转
         */
        private Boolean autoJump;

        /**
         * 图片
         */
        private List<String> pics;

        /**
         * 主标题
         */
        private String title;

        /**
         * 主内容
         */
        private String content;

        /**
         * 子标题
         */
        private String subTitle;

        /**
         * 子内容
         */
        private String subContent;


        /**
         * 接收消息平台配置
         */
        private ImFeatureConfig imFeatureConfig;

        /**
         * 链接
         */
        private List<Links> links;

    }


    @Data
    @ToString
    public static class ImFeatureConfig implements Serializable{
        private static final long serialVersionUID = 1L;

        /**
         * 跳转平台
         */
        private List<String> platforms;

        /**
         * 渲染渠道  微信：wx-proxy
         */
        private String channel;
        /**
         * 1：安卓app，2：IOS app，3：Web，4：微信小程序，5：医生工作台，7：微信
         */
        private Long appId;

        /**
         * 发送方式 枚举入参：TO_USER,TO_DOCTOR,BOTH
         */
        private String routeType;
    }


    @Data
    @ToString
    public static class Links implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 链接渠道
         */
        private String urlType;

        /**
         * 链接地址
         */
        private String url;
    }
}