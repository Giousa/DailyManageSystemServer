package com.giousa.daily.links;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LinksInfoSqlTest {

    @Test
    public void test(){

        /**
         * 渠道 代码取值:TYAPP-太医APP,WEB-WEB,TYXCX-太医小程序,H5-H5,WECHAT-微信
         */

        /**
         * 链接类型 代码取值:1-体检卡；2-家庭卡；3-名医卡；4-其他
         */

        LinksInfo linksInfo = new LinksInfo();

        linksInfo.setId(201L);

        linksInfo.setChannel("TASKS|WEB|TYXCX");
        linksInfo.setType(2);
        linksInfo.setTitle("家庭医生续费卡");
        linksInfo.setSubTitle("子标题");
        linksInfo.setSubText("子标题内容");
        linksInfo.setImage(null);
        linksInfo.setIntroduction("简介");
        linksInfo.setKeywords("续费|家庭|360元");

        linksInfo.setLinks(Arrays.asList(
                new LinksUrls("TYAPP","1111"),
                new LinksUrls("H5","22222")
        ));

        printLinksInfoSql(linksInfo);
        printLinksKeyWordsSql(linksInfo);
    }

    /**
     * INSERT INTO `links_info`(`id`, `gmt_create`, `gmt_modify`, `channel`, `title`, `ext_property`, `type`, `image`, `links`, `introduction`, `recommend_count`, `last_recommend_time`) VALUES (34, now(), now(), 'TYXCX', '体检卡-12', '{"subTitle":"体检-子标题12","subText":"这个是体检内容，欢迎您的使用，忠诚为您服务12"}', 1, '1234567894505ea23-ce22-4ca3-b2f7-f3f3234.jpg', '[{"platform":"NATIVE","url":"https://www.baidu.com?consultOrderId=${consultOrderId}"},{"platform":"H5","url":"https://www.baidu.com?appId=${appId}&userId=${userId}"},{"platform":"WORKBENCH","url":"https://www.baidu.com?appId=${appId}"},{"platform":"WX_XCX","url":"/pages/editOrder/index"}]', '这个是体检卡，方便大家使用12', 0, now());
     */
    private void printLinksInfoSql(LinksInfo linksInfo){

        System.out.println("-- ----------------------------");
        System.out.println("-- 服务卡信息");
        System.out.println("-- ----------------------------");
        Long id = linksInfo.getId();

        String channels = linksInfo.getChannel();
        String[] split = channels.split("\\|");
        for (String channel : split) {
            String sql = "INSERT INTO `links_info`(`id`, `gmt_create`, `gmt_modify`, `channel`, `title`, `ext_property`, `type`, `image`, `links`, `introduction`, `recommend_count`, `last_recommend_time`) VALUES (%d, now(), now(), '%s', '%s', ";

            StringBuffer sb = new StringBuffer(sql);
            String subTitle = linksInfo.getSubTitle();
            String subText = linksInfo.getSubText();
            if(StringUtils.isNotEmpty(subTitle)){
                //'{\"subTitle\":\"%s\",\"subText\":\"%s\"}',
                sb.append("'{\"subTitle\":\""+subTitle+"\"");
                if(StringUtils.isNotEmpty(subText)){
                    sb.append(",\"subText\":\""+subText+"\"");
                }
                sb.append("}', ");
            }else {
                if(StringUtils.isNotEmpty(subText)){
                    sb.append("'{\"subTitle\":\""+subText+"\"}', ");
                }
            }

            String image = linksInfo.getImage();
            if(StringUtils.isNotEmpty(image)){
                sb.append(" %d, '%s', ");
            }else {
                sb.append(" %d, %s, ");
            }
            List<LinksUrls> links = linksInfo.getLinks();
            if(!Objects.isNull(links) && links.size() > 0){

                int size = links.size();
                for (int i = 0; i < links.size(); i++) {
                    String platform = links.get(i).getPlatform();
                    String url = links.get(i).getUrl();
                    if(size == 1){
                        sb.append("'[{\"platform\":\""+platform+"\",\"url\":\""+url+"\"}]'");
                    }else {
                        if(i == 0){
                            sb.append("'[{\"platform\":\""+platform+"\",\"url\":\""+url+"\"}");
                        }else {
                            if(i == size - 1){
                                sb.append(", {\"platform\":\""+platform+"\",\"url\":\""+url+"\"}]'");
                            }else {
                                sb.append(", {\"platform\":\""+platform+"\",\"url\":\""+url+"\"}");
                            }
                        }
                    }

                }
            }

            sb.append(", '%s', 0, now());\n");

            String sqlText = sb.toString();
            System.out.printf(sqlText,id,channel,linksInfo.getTitle(),linksInfo.getType(),linksInfo.getImage(),linksInfo.getIntroduction());
            id++;
        }



    }

    private void printLinksKeyWordsSql(LinksInfo linksInfo) {
        System.out.println("-- ----------------------------");
        System.out.println("-- 服务卡关键词");
        System.out.println("-- ----------------------------");
        Long id = linksInfo.getId();

        String channels = linksInfo.getChannel();
        String[] split = channels.split("\\|");
        for (String channel : split) {
            String sql = "INSERT INTO `links_keyword`(`gmt_create`, `gmt_modify`, `keyword`, `links_id`) VALUES (now(), now(), '%s', %d);\n";

            String keywords = linksInfo.getKeywords();
            for(String keyword : keywords.split("\\|")){
                System.out.printf(sql,keyword,id);
            }
            id++;
        }
    }
}
