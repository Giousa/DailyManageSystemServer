package com.giousa.daily.im;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ImMsgConfigDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     *   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     *   `scene` varchar(100) NOT NULL COMMENT '业务场景(IM_WECHAT_ADD_FRIEND-添加微信好友,IM_PRIVATE_DOCTOR_BOUND-绑定医生,IM_WECHAT_BUILD_GROUP-建群...)',
     *   `im_type` varchar(64) NOT NULL COMMENT '消息类型(EMPTY-不发送,MSG-话术,CARD-卡片,IMG-图片,TIP_DOCTOR-医生信息素,TIP_COMMON-通用信息素...)',
     *   `trigger_type` varchar(32) NOT NULL COMMENT '触发类型(DEFAULT-默认，CONSULTINT-问诊中)',
     *   `biz_code` varchar(64) DEFAULT NULL COMMENT '业务码(DEFAULT-默认)',
     *   `im_content` text COMMENT '消息体',
     *   `interval_time` int(4) DEFAULT NULL COMMENT '间隔时间(单位分钟)',
     *   `app_id` bigint(4) DEFAULT NULL COMMENT 'appid',
     *   `channel` varchar(16) DEFAULT NULL COMMENT '渲染渠道',
     *   `platforms` varchar(64) DEFAULT NULL COMMENT '跳转平台',
     *   `route_type` varchar(16) DEFAULT NULL COMMENT '路由类型',
     *   `sort` int(4) NOT NULL COMMENT '排序值(按从大到小顺序依次发送)',
     *   `ext_property` text COMMENT '扩展信息',
     *   `env` varchar(10) NOT NULL COMMENT '配置环境',
     *   `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     */

    //id	scene	im_type	trigger_type	biz_code	im_content	interval_time	app_id	channel	platforms	route_type	sort	ext_property	env	create_at	update_at

    /**
     * 注意：不能有Integer
     */
    @ExcelProperty(value = "id",index = 1)
    private String id;

    @ExcelProperty(value = "scene",index = 2)
    private String scene;

    @ExcelProperty(value = "im_type",index = 3)
    private String im_type;

    @ExcelProperty(value = "trigger_type",index = 4)
    private String trigger_type;

    @ExcelProperty(value = "biz_code",index = 5)
    private String biz_code;

    @ExcelProperty(value = "im_content",index = 6)
    private String im_content;

    @ExcelProperty(value = "interval_time",index = 7)
    private String interval_time;

    @ExcelProperty(value = "app_id",index = 8)
    private String app_id;

    @ExcelProperty(value = "channel",index = 9)
    private String channel;

    @ExcelProperty(value = "platforms",index = 10)
    private String platforms;

    @ExcelProperty(value = "route_type",index = 11)
    private String route_type;

    @ExcelProperty(value = "sort",index = 12)
    private String sort;

    @ExcelProperty(value = "env",index = 14)
    private String env;

}
