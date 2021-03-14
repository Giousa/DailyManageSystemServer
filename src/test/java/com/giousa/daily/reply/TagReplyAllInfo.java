package com.giousa.daily.reply;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TagReplyAllInfo implements Serializable {

    private List<TagDTO> tagList;

    private List<TagRelDTO> tagRelList;

    private List<ReplyDTO> replyList;

    private List<ReplyRelDTO> replyRelList;

}
