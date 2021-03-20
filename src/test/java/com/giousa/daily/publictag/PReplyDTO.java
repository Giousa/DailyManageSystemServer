package com.giousa.daily.publictag;

import lombok.Data;

import java.io.Serializable;

@Data
public class PReplyDTO implements Serializable {

    private Long id;

    private Long tagId;

    private String replyContent;
}
