package com.giousa.daily.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/18
 * Email:65489469@qq.com
 */
@Component
public class ScheduledMsg {


    /**
     * 定时任务方法
     * 时间到了，就会触发
     * cron:cron表达式，对于任务触发时间的字符串表达形式
     * 从0分开始，没隔10分钟，执行一次
     * 0 0/10 * * * ?
     * 从0分开始，没隔5分钟，执行一次
     * 0 0/5 * * * ?
     * 从0分开始，没隔1分钟，执行一次
     * 0 0/1 * * * ?
     */
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void scheduledMethod(){
        System.out.println("定时器：："+new Date());

    }


}
