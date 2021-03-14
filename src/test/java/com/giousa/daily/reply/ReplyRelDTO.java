package com.giousa.daily.reply;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReplyRelDTO implements Serializable {

    private Long userId;

    private Long replyId;

    private Integer replySort;
}
