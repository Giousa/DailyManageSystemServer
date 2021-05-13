package com.giousa.daily.reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO implements Serializable {

    private Long id;

    private String tagName;

    private Long userId;

}
