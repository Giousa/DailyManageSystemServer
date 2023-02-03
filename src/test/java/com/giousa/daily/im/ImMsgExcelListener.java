package com.giousa.daily.im;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class ImMsgExcelListener extends AnalysisEventListener<ImMsgConfigDTO> {

    private List<ImMsgConfigDTO> imMsgConfigsPre = Lists.newArrayList();
    private List<ImMsgConfigDTO> imMsgConfigsProd = Lists.newArrayList();

    private List<ImMsgConfigInfo> pres = Lists.newArrayList();
    private List<ImMsgConfigInfo> prods = Lists.newArrayList();

    //一行一行读取excel内容
    @Override
    public void invoke(ImMsgConfigDTO data, AnalysisContext analysisContext) {
//        System.out.println("invoke 读取数据："+data);
        if(Objects.equals(data.getEnv(),"prod")){
            imMsgConfigsProd.add(data);
        }else if(Objects.equals(data.getEnv(),"pre")){
            imMsgConfigsPre.add(data);
        }

    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
        //表头：{0=null, 1=id, 2=scene, 3=im_type, 4=trigger_type, 5=biz_code, 6=im_content, 7=interval_time, 8=app_id, 9=channel, 10=platforms, 11=route_type, 12=sort, 13=ext_property, 14=env, 15=create_at, 16=update_at}
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("read pre over >>>> "+ JSON.toJSONString(imMsgConfigsPre));
        System.out.println("read prod over >>>> "+ JSON.toJSONString(imMsgConfigsProd));

        prods = imMsgConfigsProd.stream().map(ImMsgExcelListener::convertToImMsg).collect(Collectors.toList());
        pres = imMsgConfigsPre.stream().map(ImMsgExcelListener::convertToImMsg).collect(Collectors.toList());

        printProd();
        printPre();
    }

    private static ImMsgConfigInfo convertToImMsg(ImMsgConfigDTO dto) {
        if(Objects.isNull(dto)){
            return null;
        }
        ImMsgConfigInfo imMsgConfigInfo = new ImMsgConfigInfo();
        imMsgConfigInfo.setScene(dto.getScene());
        imMsgConfigInfo.setImType(dto.getIm_type());
        imMsgConfigInfo.setTriggerType(dto.getTrigger_type());
        imMsgConfigInfo.setBizCode(dto.getBiz_code());
        imMsgConfigInfo.setImContent(Objects.equals("None",dto.getIm_content()) ? null : dto.getIm_content());
        imMsgConfigInfo.setPlatforms(dto.getPlatforms());
        imMsgConfigInfo.setRouteType(dto.getRoute_type());
        imMsgConfigInfo.setSort(Integer.valueOf(dto.getSort()));

        //nan  .0
        if(Objects.equals("nan",dto.getApp_id())){
            imMsgConfigInfo.setAppId(null);
        }else {
            if(dto.getApp_id().contains(".")){
                String[] split = dto.getApp_id().split("\\.");
                imMsgConfigInfo.setAppId(Integer.valueOf(split[0]));
            }else {
                imMsgConfigInfo.setAppId(Integer.valueOf(dto.getApp_id()));
            }
        }
        imMsgConfigInfo.setChannel(Objects.equals(7L,imMsgConfigInfo.getAppId()) ? "wx-proxy" : null);

        return imMsgConfigInfo;
    }


    private void printProd(){
        System.out.println("-- ----------------------------");
        System.out.println("-- prod");
        System.out.println("-- ----------------------------");
        prods.stream().forEach(it -> {
            String insert = "INSERT INTO `im_msg_config` (`scene`, `im_type`, `trigger_type`, `biz_code`, `im_content`,`app_id`, `channel`, `platforms`, `route_type`, `sort`, `env`) VALUES ('%s', '%s', '%s', '%s', '%s', %d, %s, '%s', '%s', %s,'%s');\n";
            if(StringUtils.isBlank(it.getImContent())){
                insert = "INSERT INTO `im_msg_config` (`scene`, `im_type`, `trigger_type`, `biz_code`, `im_content`,`app_id`, `channel`, `platforms`, `route_type`, `sort`, `env`) VALUES ('%s', '%s', '%s', '%s', %s, %d, %s, '%s', '%s', %s,'%s');\n";
            }

            System.out.printf(insert,it.getScene(),it.getImType(),it.getTriggerType(),it.getBizCode(),it.getImContent(),it.getAppId(),it.getChannel(),it.getPlatforms(),it.getRouteType(),it.getSort(),"prod");
        });
    }

    private void printPre(){
        System.out.println("-- ----------------------------");
        System.out.println("-- pre");
        System.out.println("-- ----------------------------");
        prods.stream().forEach(it -> {
            String insert = "INSERT INTO `im_msg_config` (`scene`, `im_type`, `trigger_type`, `biz_code`, `im_content`,`app_id`, `channel`, `platforms`, `route_type`, `sort`, `env`) VALUES ('%s', '%s', '%s', '%s', '%s', %d, %s, '%s', '%s', %s,'%s');\n";
            if(StringUtils.isBlank(it.getImContent())){
                insert = "INSERT INTO `im_msg_config` (`scene`, `im_type`, `trigger_type`, `biz_code`, `im_content`,`app_id`, `channel`, `platforms`, `route_type`, `sort`, `env`) VALUES ('%s', '%s', '%s', '%s', %s, %d, %s, '%s', '%s', %s,'%s');\n";
            }

            System.out.printf(insert,it.getScene(),it.getImType(),it.getTriggerType(),it.getBizCode(),it.getImContent(),it.getAppId(),it.getChannel(),it.getPlatforms(),it.getRouteType(),it.getSort(),"pre");
        });
    }
}
