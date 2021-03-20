package com.giousa.daily.publictag;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PTagReplyAllInfo implements Serializable {

    private List<PTagDTO> tagList;

    private List<PReplyDTO> replyList;

}
