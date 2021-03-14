package com.giousa.daily.reply;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReplyDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long tagId;

    private String replyContent;
}
