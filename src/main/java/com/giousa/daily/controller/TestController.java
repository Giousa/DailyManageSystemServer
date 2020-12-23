package com.giousa.daily.controller;

import com.giousa.daily.bean.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2020/12/3
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * http://localhost:8080/daily/test/findTestInfo
     * @return
     */
    @GetMapping("/findTestInfo")
    public ResultVO findTestInfo(){

        logger.info("findTestInfo 方法调用");

        return ResultVO.ok("请求获取test数据");
    }

}
