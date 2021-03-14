package com.giousa.daily.bk;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.giousa.daily.TagWithReplyDTO;
import com.giousa.daily.reply.TagReplyAllInfo;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;

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
//        System.out.println("read over");
//
//        //从120000开始
//
//        //标签
//        List<TagDTO> tagDTOList = tagWithReplyDTOList.stream().map(this::convert).distinct().collect(Collectors.toList());
//        //标签rel
//        List<TagRelDTO> tagRelDTOList = Lists.newArrayList();
//        //快捷回复
//        List<ReplyDTO> replyDTOList = Lists.newArrayList();
//        //快捷回复rel
//        List<ReplyRelDTO> replyRelDTOList = Lists.newArrayList();
//
//
//        Long tagId = 110000l;
//        Long replyId = 110000l;
//        Integer tagSort = 1;
//        Integer replySort = 1;
//        for(TagDTO tagDTO:tagDTOList){
//            tagDTO.setId(tagId);
//
//            //筛选当前标签和userId对应的回复内容
//            List<TagWithReplyDTO> list = tagWithReplyDTOList.stream()
//                    .filter(it -> Objects.equals(it.getTagName(),tagDTO.getTagName()) && Objects.equals(it.getUserId(),tagDTO.getUserId())).distinct().collect(Collectors.toList());
//
//
//            for(TagWithReplyDTO tagWithReplyDTO : list){
//
//                //标签rel
//                TagRelDTO tagRelDTO = new TagRelDTO();
//                tagRelDTO.setUserId(tagDTO.getUserId());
//                tagRelDTO.setTagId(tagId);
//                tagRelDTO.setTagSort(tagSort);
//                tagRelDTOList.add(tagRelDTO);
//                tagSort++;
//
//                //快捷回复
//                ReplyDTO replyDTO = new ReplyDTO();
//                replyDTO.setId(replyId);
//                replyDTO.setUserId(tagWithReplyDTO.getUserId());
//                replyDTO.setTagId(tagId);
//                replyDTO.setReplyContent(tagWithReplyDTO.getReplyContent());
//                replyDTOList.add(replyDTO);
//
//                //快捷回复rel
//                ReplyRelDTO replyRelDTO = new ReplyRelDTO();
//                replyRelDTO.setUserId(tagWithReplyDTO.getUserId());
//                replyRelDTO.setReplyId(replyId);
//                replyRelDTO.setReplySort(replySort);
//                replyRelDTOList.add(replyRelDTO);
//                replySort++;
//
//                replyId++;
//
//            }
//
//            tagId++;
//            tagSort = 1;
//            replySort = 1;
//        }
//        TagReplyAllInfo tagReplyAllInfo = new TagReplyAllInfo();
//        tagReplyAllInfo.setTagList(tagDTOList);
//        tagReplyAllInfo.setTagRelList(tagRelDTOList);
//        tagReplyAllInfo.setReplyList(replyDTOList);
//        tagReplyAllInfo.setReplyRelList(replyRelDTOList);
//
//        printTagReplyAllInfo(tagReplyAllInfo);

    }


//    public TagDTO convert(TagWithReplyDTO tagWithReplyDTO){
//        TagDTO tagDTO = new TagDTO();
//        tagDTO.setUserId(tagWithReplyDTO.getUserId());
//        tagDTO.setTagName(tagWithReplyDTO.getTagName());
//        return tagDTO;
//    }



    private void printTagReplyAllInfo(TagReplyAllInfo tagReplyAllInfo) {


//        System.out.println("-- ----------------------------");
//        System.out.println("-- 标签");
//        System.out.println("-- ----------------------------");
//        List<TagDTO> tagList = tagReplyAllInfo.getTagList();
//
//        for(TagDTO tagDTO : tagList){
//            String insertTag = "INSERT INTO `quick_reply_tag`(`id`, `tag_name`, `is_custom`, `dept_id`, `status`, `source`, `gmt_create`, `gmt_modify`) VALUES (%d, '%s', 1, null, 1, 0, now(), now());\n";
//            System.out.printf(insertTag,tagDTO.getId(),tagDTO.getTagName());
//        }


//        System.out.println("-- ----------------------------");
//        System.out.println("-- 标签rel");
//        System.out.println("-- ----------------------------");
//
//        List<TagRelDTO> tagRelList = tagReplyAllInfo.getTagRelList();
//        for(TagRelDTO tagRelDTO : tagRelList){
//            String insertTagRel = "INSERT INTO `quick_reply_tag_rel`(`user_id`, `quick_reply_tag_id`, `is_show`, `sort`, `status`, `gmt_create`, `gmt_modify`) VALUES (%d, %d, 1, %d, 1, now(), now());\n";
//            System.out.printf(insertTagRel,tagRelDTO.getUserId(),tagRelDTO.getTagId(),tagRelDTO.getTagSort());
//
//        }


//        System.out.println("-- ----------------------------");
//        System.out.println("-- 回复");
//        System.out.println("-- ----------------------------");
//
//        List<ReplyDTO> replyList = tagReplyAllInfo.getReplyList();
//        for(ReplyDTO replyDTO : replyList){
//            String insertReply = "INSERT INTO `quick_reply`(`id`, `is_custom`, `content`, `status`, `tag_id`, `click_count`, `user_id`, `gmt_create`, `gmt_modify`) VALUES (%d, 1, '%s', 1, %d, 0, %d, now(), now());\n";
//            System.out.printf(insertReply,replyDTO.getId(),replyDTO.getReplyContent(),replyDTO.getTagId(),replyDTO.getUserId());
//        }


//        System.out.println("-- ----------------------------");
//        System.out.println("-- 回复rel");
//        System.out.println("-- ----------------------------");
//
//        List<ReplyRelDTO> replyRelList = tagReplyAllInfo.getReplyRelList();
//        for(ReplyRelDTO replyRelDTO : replyRelList){
//            String insertReplyRel = "INSERT INTO `quick_reply_rel`(`quick_reply_id`, `user_id`, `sort`, `status`, `gmt_create`, `gmt_modify`) VALUES (%d, %d, %d, 1, now(), now());\n";
//            System.out.printf(insertReplyRel,replyRelDTO.getReplyId(),replyRelDTO.getUserId(),replyRelDTO.getReplySort());
//        }

    }
}
