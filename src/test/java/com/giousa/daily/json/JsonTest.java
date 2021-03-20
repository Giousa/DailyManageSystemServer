package com.giousa.daily.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

public class JsonTest {


    @Test
    public void test(){
        String s = "{\"h5Url\":\"https://www.baidu.com\",\"nativeUrl\":\"https://www.baidu.com\",\"xcxUrl\":\"https://www.baidu.com\",\"workbenchUrl\":\"https://www.baidu.com\"}";
        ImCardConfigInfo cardConfig = JSONObject.parseObject(s,ImCardConfigInfo.class);
        System.out.println(cardConfig);
    }

}
