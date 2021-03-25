package com.giousa.daily.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JsonTest {


    @Test
    public void test(){
        String s = "{\"h5Url\":\"https://www.baidu.com\",\"nativeUrl\":\"https://www.baidu.com\",\"xcxUrl\":\"https://www.baidu.com\",\"workbenchUrl\":\"https://www.baidu.com\"}";
        ImCardConfigInfo cardConfig = JSONObject.parseObject(s,ImCardConfigInfo.class);
        System.out.println(cardConfig);
    }

    @Test
    public void test2(){

//        String s = "[{\"platform\":\"NATIVE\",\"url\":\"https://www.baidu.com?consultOrderId=${consultOrderId}\"},{\"platform\":\"H5\",\"url\":\"https://www.baidu.com?appId=${appId}&userId=${userId}\"},{\"platform\":\"WORKBENCH\",\"url\":\"https://www.baidu.com\"},{\"platform\":\"WX_XCX\",\"url\":\"https://www.baidu.com\"}]";
        String s = "[]";

        List<CardLinksInfo> linksList = JSON.parseArray(s
                .replaceAll("\\$\\{consultOrderId\\}", 909999090 + "")
                .replaceAll("\\$\\{userId\\}", 1111111 + "")
                .replaceAll("\\$\\{appId\\}", 222222 + "")
                .replaceAll("\\$\\{linkId\\}", 3333333 + ""), CardLinksInfo.class);

        System.out.println(linksList);

        Map<String,String> map = linksList.parallelStream().collect(Collectors.toMap(CardLinksInfo::getPlatform,CardLinksInfo::getUrl, (t1, t2) -> t1));

        System.out.println(map);

        System.out.println("::::::");
        IMPlatform imPlatform1 = IMPlatform.valueOf("NATIVE");
        System.out.println(imPlatform1);
        System.out.println("::::::");


//        String s1 = map.get(IMPlatform.WX_XCX.getCode());
//        String s2 = map.get(IMPlatform.WORKBENCH.getCode());
//        String s3 = map.get(IMPlatform.H5.getCode());
//        String s4 = map.get(IMPlatform.NATIVE.getCode());
//        System.out.println("----------");
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);
//        System.out.println("----------");


        IMPlatform[] values = IMPlatform.values();
        for(IMPlatform imPlatform : values){
            System.out.println(imPlatform);
        }

        List<IMPlatform> collect = linksList.stream().map(it -> convert(it.getPlatform())).collect(Collectors.toList());
        System.out.println(collect);
    }

    public IMPlatform convert(String platform){



        if(Objects.equals(platform,IMPlatform.NATIVE.getCode())){
            return IMPlatform.NATIVE;
        }

        if(Objects.equals(platform,IMPlatform.H5.getCode())){
            return IMPlatform.H5;
        }

        if(Objects.equals(platform,IMPlatform.WORKBENCH.getCode())){
            return IMPlatform.WORKBENCH;
        }


        if(Objects.equals(platform,IMPlatform.WX_XCX.getCode())){
            return IMPlatform.WX_XCX;
        }

        return null;

    }


    @Test
    public void test3(){
        System.out.println(Arrays.asList("IM_DOCTOR_BUTTON_CHAT","IM_DOCTOR_TAB_CONF").toString());

        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(2 << 3);
        System.out.println(5 << 3);

        List<Integer> collect = IntStream.range(1, 9).boxed().collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    public void test4(){

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                System.out.println("异常处理");
            }
            System.out.println("执行任务_"+i);
        }

    }
}
