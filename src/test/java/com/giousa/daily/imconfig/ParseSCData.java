package com.giousa.daily.imconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.assertj.core.util.Lists;

import java.util.List;

public class ParseSCData {

    public static void main(String[] args) {

        String msg = "{\"data\": [{\"sc\": \"A00101\"}, {\"sc\": \"A00102\"}, {\"sc\": \"A00103\"}, {\"sc\": \"A00104\"}, {\"sc\": \"A00107\"}, {\"sc\": \"A00110\"}, {\"sc\": \"A00113\"}, {\"sc\": \"A00114\"}, {\"sc\": \"A00115\"}, {\"sc\": \"A00116\"}, {\"sc\": \"A00117\"}, {\"sc\": \"A00118\"}, {\"sc\": \"A00119\"}, {\"sc\": \"A00121\"}, {\"sc\": \"A00122\"}, {\"sc\": \"A00123\"}, {\"sc\": \"A00125\"}, {\"sc\": \"A00128\"}, {\"sc\": \"A00129\"}, {\"sc\": \"A00133\"}, {\"sc\": \"A00134\"}, {\"sc\": \"A00135\"}, {\"sc\": \"A00138\"}, {\"sc\": \"A00140\"}, {\"sc\": \"A00108\"}, {\"sc\": \"A00137\"}], \"title\": [{\"title\": \"sc\", \"key\": \"sc\", \"fixed\": \"left\", \"sortable\": true, \"align\": \"center\", \"resizable\": true, \"width\": 102, \"comment\": null}], \"len\": 26, \"spend_time\": 0.0011, \"pagename\": \"selectexe\"}";

        JSONObject jsonObject = JSON.parseObject(msg);

        String data = jsonObject.getString("data");

        JSONArray jsonArray = JSON.parseArray(data);
        List<String> scList = Lists.newArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            String sc = jsonArray.getJSONObject(i).getString("sc");
            System.out.println("SC = "+sc);
            scList.add(sc);
        }

        //["A00101","A00102","A00103","A00104","A00107","A00110","A00113","A00114","A00115","A00116","A00117","A00118","A00119","A00121","A00122","A00123","A00125","A00128","A00129","A00133","A00134","A00135","A00138","A00140","A00108","A00137"]
        System.out.println("scList >>>> "+JSON.toJSONString(scList));
    }
}
