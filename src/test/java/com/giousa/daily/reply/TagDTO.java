package com.giousa.daily.reply;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagDTO implements Serializable {

    private Long id;

    private String tagName;

    private Long userId;

}
