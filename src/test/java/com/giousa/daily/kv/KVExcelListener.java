package com.giousa.daily.kv;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

@Slf4j
public class KVExcelListener extends AnalysisEventListener<KVExcelParseDTO> {

    private List<KVExcelParseDTO> list = Lists.newArrayList();
    private Map<String,String> kvMap = Maps.newHashMap();
    private String env = "dev";

    @Override
    public void invoke(KVExcelParseDTO kvExcelParseDTO, AnalysisContext analysisContext) {
        list.add(kvExcelParseDTO);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("read over");

        if(CollectionUtils.isEmpty(list)){
            System.out.println("数据为空");
            return;
        }

        list.stream().forEach(it -> {

            if(StringUtils.isEmpty(it.getDoctorId())){
                return;
            }

            if(StringUtils.isEmpty(it.getIm1()) && StringUtils.isEmpty(it.getCardTitle1()) && StringUtils.isEmpty(it.getCardH51())){
                return;
            }

            KVDoctorIMConfigInfo kvDoctorIMConfigInfo = new KVDoctorIMConfigInfo();


            List<KVDoctorIMConfigInfo.ImMessageConfig> imMessageConfig = Lists.newArrayList();
            List<KVDoctorIMConfigInfo.ImCardConfig> imCardConfig = Lists.newArrayList();
            List<KVDoctorIMConfigInfo.ImImageConfig> imImageConfig = Lists.newArrayList();

            if(StringUtils.isNotEmpty(it.getIm1())){
                KVDoctorIMConfigInfo.ImFeatureConfig imFeatureConfig = new KVDoctorIMConfigInfo.ImFeatureConfig();
                imFeatureConfig.setAppId(7L);
                imFeatureConfig.setChannel("wx-proxy");

                KVDoctorIMConfigInfo.ImMessageConfig config = new KVDoctorIMConfigInfo.ImMessageConfig();
                config.setImFeatureConfig(imFeatureConfig);
                config.setSort(it.getImSort1() == null ? 30 : it.getImSort1());
                config.setMessage(it.getIm1());
                imMessageConfig.add(config);
            }

            if(StringUtils.isNotEmpty(it.getIm2())){

                KVDoctorIMConfigInfo.ImFeatureConfig imFeatureConfig = new KVDoctorIMConfigInfo.ImFeatureConfig();
                imFeatureConfig.setAppId(7L);
                imFeatureConfig.setChannel("wx-proxy");


                KVDoctorIMConfigInfo.ImMessageConfig config = new KVDoctorIMConfigInfo.ImMessageConfig();
                config.setImFeatureConfig(imFeatureConfig);
                config.setSort(it.getImSort2() == null ? 20 : it.getImSort2());
                config.setMessage(it.getIm2());
                imMessageConfig.add(config);
            }

            if(StringUtils.isNotEmpty(it.getIm3())){

                KVDoctorIMConfigInfo.ImFeatureConfig imFeatureConfig = new KVDoctorIMConfigInfo.ImFeatureConfig();
                imFeatureConfig.setAppId(7L);
                imFeatureConfig.setChannel("wx-proxy");

                KVDoctorIMConfigInfo.ImMessageConfig config = new KVDoctorIMConfigInfo.ImMessageConfig();
                config.setImFeatureConfig(imFeatureConfig);
                config.setSort(it.getImSort3() == null ? 10 : it.getImSort3());
                config.setMessage(it.getIm3());
                imMessageConfig.add(config);
            }

            if(StringUtils.isNotEmpty(it.getCardTitle1()) && StringUtils.isNotEmpty(it.getCardH51())){

                KVDoctorIMConfigInfo.ImFeatureConfig imFeatureConfig = new KVDoctorIMConfigInfo.ImFeatureConfig();
                imFeatureConfig.setAppId(7L);
                imFeatureConfig.setChannel("wx-proxy");

                KVDoctorIMConfigInfo.ImCardConfig config = new KVDoctorIMConfigInfo.ImCardConfig();
                config.setImFeatureConfig(imFeatureConfig);
                config.setSort(it.getCardSort1() == null ? 120 : it.getCardSort1());
                config.setTitle(it.getCardTitle1());
                config.setContent(it.getCardContent1());
                config.setPics(Lists.newArrayList(it.getCardPic1()));
                List<KVDoctorIMConfigInfo.Links> links = Lists.newArrayList();
                if(StringUtils.isNotEmpty(it.getCardH51())){
                    KVDoctorIMConfigInfo.Links link = new KVDoctorIMConfigInfo.Links();
                    link.setUrlType("H5");
                    link.setUrl(it.getCardH51());
                    links.add(link);
                }
                if(StringUtils.isNotEmpty(it.getCardXCX1())){
                    KVDoctorIMConfigInfo.Links link = new KVDoctorIMConfigInfo.Links();
                    link.setUrlType("WX_XCX");
                    link.setUrl(it.getCardXCX1());
                    links.add(link);
                }

                if(StringUtils.isNotEmpty(it.getCardWX1())){
                    KVDoctorIMConfigInfo.Links link = new KVDoctorIMConfigInfo.Links();
                    link.setUrlType("WECHAT");
                    link.setUrl(it.getCardWX1());
                    links.add(link);
                }

                if(CollectionUtils.isNotEmpty(links)){
                    config.setLinks(links);
                }

                imCardConfig.add(config);
            }

            if(StringUtils.isNotEmpty(it.getCardTitle2()) && StringUtils.isNotEmpty(it.getCardH52())){

                KVDoctorIMConfigInfo.ImFeatureConfig imFeatureConfig = new KVDoctorIMConfigInfo.ImFeatureConfig();
                imFeatureConfig.setAppId(7L);
                imFeatureConfig.setChannel("wx-proxy");

                KVDoctorIMConfigInfo.ImCardConfig config = new KVDoctorIMConfigInfo.ImCardConfig();
                config.setImFeatureConfig(imFeatureConfig);
                config.setSort(it.getCardSort2() == null ? 100 : it.getCardSort2());
                config.setTitle(it.getCardTitle2());
                config.setContent(it.getCardContent2());
                config.setPics(Lists.newArrayList(it.getCardPic2()));
                List<KVDoctorIMConfigInfo.Links> links = Lists.newArrayList();
                if(StringUtils.isNotEmpty(it.getCardH52())){
                    KVDoctorIMConfigInfo.Links link = new KVDoctorIMConfigInfo.Links();
                    link.setUrlType("H5");
                    link.setUrl(it.getCardH52());
                    links.add(link);
                }
                if(StringUtils.isNotEmpty(it.getCardXCX2())){
                    KVDoctorIMConfigInfo.Links link = new KVDoctorIMConfigInfo.Links();
                    link.setUrlType("WX_XCX");
                    link.setUrl(it.getCardXCX2());
                    links.add(link);
                }

                if(StringUtils.isNotEmpty(it.getCardWX2())){
                    KVDoctorIMConfigInfo.Links link = new KVDoctorIMConfigInfo.Links();
                    link.setUrlType("WECHAT");
                    link.setUrl(it.getCardWX2());
                    links.add(link);
                }

                if(CollectionUtils.isNotEmpty(links)){
                    config.setLinks(links);
                }

                imCardConfig.add(config);

            }

            if(StringUtils.isNotEmpty(it.getPic1())){
                KVDoctorIMConfigInfo.ImFeatureConfig imFeatureConfig = new KVDoctorIMConfigInfo.ImFeatureConfig();
                imFeatureConfig.setAppId(7L);
                imFeatureConfig.setChannel("wx-proxy");

                KVDoctorIMConfigInfo.ImImageConfig config = new KVDoctorIMConfigInfo.ImImageConfig();
                config.setImFeatureConfig(imFeatureConfig);
                config.setSort(it.getPicSort1() == null ? 80 : it.getPicSort1());
                config.setImage(it.getPic1());
                imImageConfig.add(config);
            }

            if(StringUtils.isNotEmpty(it.getPic2())){
                KVDoctorIMConfigInfo.ImFeatureConfig imFeatureConfig = new KVDoctorIMConfigInfo.ImFeatureConfig();
                imFeatureConfig.setAppId(7L);
                imFeatureConfig.setChannel("wx-proxy");

                KVDoctorIMConfigInfo.ImImageConfig config = new KVDoctorIMConfigInfo.ImImageConfig();
                config.setImFeatureConfig(imFeatureConfig);
                config.setSort(it.getPicSort2() == null ? 70 : it.getPicSort2());
                config.setImage(it.getPic2());
                imImageConfig.add(config);
            }


            if(CollectionUtils.isNotEmpty(imMessageConfig)){
                kvDoctorIMConfigInfo.setImMessageConfig(imMessageConfig);
            }

            if(CollectionUtils.isNotEmpty(imCardConfig)){
                kvDoctorIMConfigInfo.setImCardConfig(imCardConfig);
            }

            if(CollectionUtils.isNotEmpty(imImageConfig)){
                kvDoctorIMConfigInfo.setImImageConfig(imImageConfig);
            }

            String str = JSON.toJSONString(kvDoctorIMConfigInfo);
            System.out.println(str);

            kvMap.put(it.getDoctorId(),str);
        });

        log.info("准备开始写入数据：");
        writeFile();

    }


    private void writeFile() {
        // 在文件夹目录下新建文件
        String fileName = "/Users/zhangmengmeng/Desktop/ExcelFiles/kv.json";
        File file = new File(fileName);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    log.info("文件不存在，开始创建文件");
                }
                fos = new FileOutputStream(file);
            } else {
                //清空文件
                clearFile(file);
                //追加内容
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");

            for(Map.Entry<String, String> it : kvMap.entrySet()){
                // 写入内容
                String insertTag = "INSERT INTO `key_value_config` (config_type, config_key, config_value,env) VALUES ('IM_WX_DOCTOR_FRIEND', '%s', '%s','%s');\n";
                String format = String.format(insertTag,it.getKey(),kvMap.get(it.getKey()),env);
                osw.write(format);
            }

            log.info("文件:{} ; 写入完成！",fileName);
        } catch (Exception e) {
            log.info("写入文件发生异常", e);
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.info("关闭流异常", e);
            }
        }

    }

    private void clearFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            log.info("文件清理成功！");
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("文件清理失败！");
        }

    }
}
