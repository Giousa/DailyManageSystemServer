package com.giousa.daily;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.giousa.daily.reply.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExcelListener extends AnalysisEventListener<TagWithReplyDTO> {

    private List<TagWithReplyDTO> tagWithReplyDTOList = Lists.newArrayList();

    //一行一行读取excel内容
    @Override
    public void invoke(TagWithReplyDTO data, AnalysisContext analysisContext) {
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
//        List<TagDTO> tagDTOList = tagWithReplyDTOList.stream().map(this::convert).distinct().collect(Collectors.toList());
        List<TagDTO> tagDTOList = Lists.newArrayList();
        //标签rel
        List<TagRelDTO> tagRelDTOList = Lists.newArrayList();
        //快捷回复
        List<ReplyDTO> replyDTOList = Lists.newArrayList();
        //快捷回复rel
        List<ReplyRelDTO> replyRelDTOList = Lists.newArrayList();

        Map<Long,Map<String,List<String>>> map = new LinkedHashMap<>();
        for(TagWithReplyDTO tagWithReplyDTO : tagWithReplyDTOList){
            Long userId = tagWithReplyDTO.getUserId();
            String tagName = tagWithReplyDTO.getTagName();
            String replyContent = tagWithReplyDTO.getReplyContent();
            Map<String, List<String>> tagMap;
            if(map.containsKey(userId)){
                tagMap = map.get(userId);
                List<String> relpyList;
                if(tagMap.containsKey(tagName)){
                    relpyList = tagMap.get(tagName);
                }else {
                    relpyList = Lists.newArrayList();
                }
                relpyList.add(replyContent);
                tagMap.put(tagName,relpyList);
                map.put(userId,tagMap);

            }else {
                tagMap = new HashMap<>();
                List<String> relpyList = Lists.newArrayList();
                relpyList.add(replyContent);
                tagMap.put(tagName,relpyList);
                map.put(userId,tagMap);
            }
        }

        Long tagId = 120000l;
        Long replyId = 120000l;
        Integer tagSort;
        Integer replySort;

        for(Map.Entry<Long,Map<String,List<String>>> entry : map.entrySet()){
            Long userId = entry.getKey();
            Map<String, List<String>> tagMap = entry.getValue();
            tagSort = 1;
            for(String tagName : tagMap.keySet()){
                //标签
                TagDTO tagDTO = new TagDTO();
                tagDTO.setId(tagId);
                tagDTO.setUserId(userId);
                tagDTO.setTagName(tagName);
                tagDTOList.add(tagDTO);

                //标签rel
                TagRelDTO tagRelDTO = new TagRelDTO();
                tagRelDTO.setUserId(userId);
                tagRelDTO.setTagId(tagId);
//                tagRelDTO.setTagSort(tagSort);
                tagRelDTO.setTagSort(99);
                tagRelDTOList.add(tagRelDTO);

                List<String> replyContentList = tagMap.get(tagName);
                replySort = 1;
                for(String replyContent : replyContentList){
                    //回复
                    ReplyDTO replyDTO = new ReplyDTO();
                    replyDTO.setId(replyId);
                    replyDTO.setUserId(userId);
                    replyDTO.setReplyContent(replyContent);
                    replyDTO.setTagId(tagId);
                    replyDTOList.add(replyDTO);

                    //回复rel
                    ReplyRelDTO replyRelDTO = new ReplyRelDTO();
                    replyRelDTO.setUserId(userId);
                    replyRelDTO.setReplyId(replyId);
                    replyRelDTO.setReplySort(replySort);
                    replyRelDTOList.add(replyRelDTO);


                    replyId++;
                    replySort++;
                }



                tagId++;
                tagSort++;
            }
        }

        TagReplyAllInfo tagReplyAllInfo = new TagReplyAllInfo();
        tagReplyAllInfo.setTagList(tagDTOList);
        tagReplyAllInfo.setTagRelList(tagRelDTOList);
        tagReplyAllInfo.setReplyList(replyDTOList);
        tagReplyAllInfo.setReplyRelList(replyRelDTOList);

//        printTagReplyAllInfo(tagReplyAllInfo);

        writeFile(1,tagReplyAllInfo);
        writeFile(2,tagReplyAllInfo);
        writeFile(3,tagReplyAllInfo);
        writeFile(4,tagReplyAllInfo);


    }

    private void printTagReplyAllInfo(TagReplyAllInfo tagReplyAllInfo) {


        System.out.println("-- ----------------------------");
        System.out.println("-- 标签");
        System.out.println("-- ----------------------------");
        List<TagDTO> tagList = tagReplyAllInfo.getTagList();

        for(TagDTO tagDTO : tagList){
            String insertTag = "INSERT INTO `quick_reply_tag`(`id`, `tag_name`, `is_custom`, `dept_id`, `status`, `source`, `gmt_create`, `gmt_modify`) VALUES (%d, '%s', 1, null, 1, 0, now(), now());\n";
            System.out.printf(insertTag,tagDTO.getId(),tagDTO.getTagName());
        }


        System.out.println("-- ----------------------------");
        System.out.println("-- 标签rel");
        System.out.println("-- ----------------------------");

        List<TagRelDTO> tagRelList = tagReplyAllInfo.getTagRelList();
        for(TagRelDTO tagRelDTO : tagRelList){
            String insertTagRel = "INSERT INTO `quick_reply_tag_rel`(`user_id`, `quick_reply_tag_id`, `is_show`, `sort`, `status`, `gmt_create`, `gmt_modify`) VALUES (%d, %d, 0, %d, 1, now(), now());\n";
            System.out.printf(insertTagRel,tagRelDTO.getUserId(),tagRelDTO.getTagId(),tagRelDTO.getTagSort());

        }


        System.out.println("-- ----------------------------");
        System.out.println("-- 回复");
        System.out.println("-- ----------------------------");

        List<ReplyDTO> replyList = tagReplyAllInfo.getReplyList();
        for(ReplyDTO replyDTO : replyList){
            String insertReply = "INSERT INTO `quick_reply`(`id`, `is_custom`, `content`, `status`, `tag_id`, `click_count`, `user_id`, `gmt_create`, `gmt_modify`) VALUES (%d, 1, '%s', 1, %d, 0, %d, now(), now());\n";
            System.out.printf(insertReply,replyDTO.getId(),replyDTO.getReplyContent(),replyDTO.getTagId(),replyDTO.getUserId());
        }


        System.out.println("-- ----------------------------");
        System.out.println("-- 回复rel");
        System.out.println("-- ----------------------------");

        List<ReplyRelDTO> replyRelList = tagReplyAllInfo.getReplyRelList();
        for(ReplyRelDTO replyRelDTO : replyRelList){
            String insertReplyRel = "INSERT INTO `quick_reply_rel`(`quick_reply_id`, `user_id`, `sort`, `status`, `gmt_create`, `gmt_modify`) VALUES (%d, %d, %d, 1, now(), now());\n";
            System.out.printf(insertReplyRel,replyRelDTO.getReplyId(),replyRelDTO.getUserId(),replyRelDTO.getReplySort());
        }


    }

    private String baseFilePath = "/Users/zhangmengmeng/Downloads/test/快捷回复/sqltest/";

    private void writeFile(Integer fileType,TagReplyAllInfo tagReplyAllInfo) {

        // 在文件夹目录下新建文件
        String fileName;
        String fileTopName;
        switch (fileType){
            case 1:
                fileName = baseFilePath + "quick_reply_tag_2_0_0.sql";
                fileTopName = "-- 签名\n";
                break;

            case 2:
                fileName = baseFilePath + "quick_reply_tag_rel_2_0_0.sql";
                fileTopName = "-- 签名rel\n";
                break;

            case 3:
                fileName = baseFilePath + "quick_reply_2_0_0.sql";
                fileTopName = "-- 回复\n";
                break;

            case 4:
                fileName = baseFilePath + "quick_reply_rel_2_0_0.sql";
                fileTopName = "-- 回复rel\n";
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
                    for(TagDTO tagDTO : tagReplyAllInfo.getTagList()){
                        String insertTag = "INSERT INTO `quick_reply_tag`(`id`, `tag_name`, `is_custom`, `dept_id`, `status`, `source`, `gmt_create`, `gmt_modify`) VALUES (%d, '%s', 1, null, 1, 0, now(), now());\n";
                        String format = String.format(insertTag,tagDTO.getId(),tagDTO.getTagName());
                        osw.write(format);
                    }
                    break;

                case 2:
                    for(TagRelDTO tagRelDTO : tagReplyAllInfo.getTagRelList()){
                        String insertTagRel = "INSERT INTO `quick_reply_tag_rel`(`user_id`, `quick_reply_tag_id`, `is_show`, `sort`, `status`, `gmt_create`, `gmt_modify`) VALUES (%d, %d, 0, %d, 1, now(), now());\n";
                        String format = String.format(insertTagRel,tagRelDTO.getUserId(),tagRelDTO.getTagId(),tagRelDTO.getTagSort());
                        osw.write(format);
                    }
                    break;

                case 3:
                    for(ReplyDTO replyDTO : tagReplyAllInfo.getReplyList()){
                        String insertReply = "INSERT INTO `quick_reply`(`id`, `is_custom`, `content`, `status`, `tag_id`, `click_count`, `user_id`, `gmt_create`, `gmt_modify`) VALUES (%d, 1, '%s', 1, %d, 0, %d, now(), now());\n";
                        String format = String.format(insertReply,replyDTO.getId(),replyDTO.getReplyContent(),replyDTO.getTagId(),replyDTO.getUserId());
                        osw.write(format);
                    }

                    break;

                case 4:
                    for(ReplyRelDTO replyRelDTO : tagReplyAllInfo.getReplyRelList()){
                        String insertReplyRel = "INSERT INTO `quick_reply_rel`(`quick_reply_id`, `user_id`, `sort`, `status`, `gmt_create`, `gmt_modify`) VALUES (%d, %d, %d, 1, now(), now());\n";
                        String format = String.format(insertReplyRel, replyRelDTO.getReplyId(), replyRelDTO.getUserId(), replyRelDTO.getReplySort());
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
