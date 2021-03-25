package com.giousa.daily.publictag;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class PublicExcelListener extends AnalysisEventListener<PTagWithReplyDTO> {

    private List<PTagWithReplyDTO> tagWithReplyDTOList = Lists.newArrayList();

    //一行一行读取excel内容
    @Override
    public void invoke(PTagWithReplyDTO data, AnalysisContext analysisContext) {
//        System.out.println("invoke 读取数据："+data);
        tagWithReplyDTOList.add(data);


    }
    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
        //表头：{0=助手名, 1=助手id, 2=标签名, 3=快捷回复, 4=助手id}
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("read over");

        //从120000开始

        //标签
        List<PTagDTO> tagDTOList = Lists.newArrayList();
        //快捷回复
        List<PReplyDTO> replyDTOList = Lists.newArrayList();

        Map<Long,Map<String,List<String>>> map = new LinkedHashMap<>();
        for(PTagWithReplyDTO tagWithReplyDTO : tagWithReplyDTOList){
            Long deptId = tagWithReplyDTO.getDeptId();
            String tagName = tagWithReplyDTO.getTagName();
            String replyContent = tagWithReplyDTO.getReplyContent();
            Map<String, List<String>> tagMap;
            if(map.containsKey(deptId)){
                tagMap = map.get(deptId);
                List<String> relpyList;
                if(tagMap.containsKey(tagName)){
                    relpyList = tagMap.get(tagName);
                }else {
                    relpyList = Lists.newArrayList();
                }
                relpyList.add(replyContent);
                tagMap.put(tagName,relpyList);
                map.put(deptId,tagMap);

            }else {
                tagMap = new HashMap<>();
                List<String> relpyList = Lists.newArrayList();
                relpyList.add(replyContent);
                tagMap.put(tagName,relpyList);
                map.put(deptId,tagMap);
            }
        }

        Long tagId = 120000l;
        Long replyId = 120000l;

        for(Map.Entry<Long,Map<String,List<String>>> entry : map.entrySet()){
            Long deptId = entry.getKey();
            Map<String, List<String>> tagMap = entry.getValue();
            for(String tagName : tagMap.keySet()){
                //标签
                PTagDTO tagDTO = new PTagDTO();
                tagDTO.setId(tagId);
                tagDTO.setDeptId(deptId);
                tagDTO.setTagName(tagName);
                tagDTOList.add(tagDTO);

                List<String> replyContentList = tagMap.get(tagName);
                for(String replyContent : replyContentList){
                    //回复
                    PReplyDTO replyDTO = new PReplyDTO();
                    replyDTO.setId(replyId);
                    replyDTO.setReplyContent(replyContent);
                    replyDTO.setTagId(tagId);
                    replyDTOList.add(replyDTO);

                    replyId++;
                }

                tagId++;
            }
        }

        PTagReplyAllInfo tagReplyAllInfo = new PTagReplyAllInfo();
        tagReplyAllInfo.setTagList(tagDTOList);
        tagReplyAllInfo.setReplyList(replyDTOList);

        writeFile(1,tagReplyAllInfo);
        writeFile(3,tagReplyAllInfo);


    }

    private String baseFilePath = "/Users/zhangmengmeng/Downloads/test/快捷回复/sqlpublic/";

    private void writeFile(Integer fileType,PTagReplyAllInfo tagReplyAllInfo) {

        // 在文件夹目录下新建文件
        String fileName;
        String fileTopName;
        switch (fileType){
            case 1:
                fileName = baseFilePath + "quick_reply_tag_2_0_0.sql";
                fileTopName = "-- 签名\n";
                break;

            case 3:
                fileName = baseFilePath + "quick_reply_2_0_0.sql";
                fileTopName = "-- 回复\n";
                break;

            default:
                return;
        }
        File file = new File(fileName);

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    log.info("file not exists, create new file");
                }
                fos = new FileOutputStream(file);
            } else {
                //清空文件
                clearFile(file);

                //追加内容
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");
            // 写入内容
            osw.write("-- ----------------------------\n");
            osw.write(fileTopName);
            osw.write("-- ----------------------------\n");
            osw.write("use consult_workbench;\n\n");


            switch (fileType){
                case 1:
                    for(PTagDTO tagDTO : tagReplyAllInfo.getTagList()){
                        String insertTag = "INSERT INTO `quick_reply_tag`(`id`, `tag_name`, `is_custom`, `dept_id`, `status`, `source`, `gmt_create`, `gmt_modify`) VALUES (%d, '%s', 0, %s, 1, 0, now(), now());\n";
                        String format = String.format(insertTag,tagDTO.getId(),tagDTO.getTagName(),tagDTO.getDeptId());
                        osw.write(format);
                    }
                    break;

                case 3:
                    for(PReplyDTO replyDTO : tagReplyAllInfo.getReplyList()){
                        String insertReply = "INSERT INTO `quick_reply`(`id`, `is_custom`, `content`, `status`, `tag_id`, `click_count`, `user_id`, `gmt_create`, `gmt_modify`) VALUES (%d, 0, '%s', 1, %d, 0, 0, now(), now());\n";
                        String format = String.format(insertReply,replyDTO.getId(),replyDTO.getReplyContent(),replyDTO.getTagId());
                        osw.write(format);
                    }

                    break;

                default:
                    return;
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
            log.info("file clear success！");
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("file clear fail");
        }

    }

}
