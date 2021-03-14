package com.giousa.daily.reply;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagRelDTO implements Serializable {

    private Long userId;

    private Long tagId;

    private Integer tagSort;
}
