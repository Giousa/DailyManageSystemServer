package com.giousa.daily.publictag;

import lombok.Data;

import java.io.Serializable;

@Data
public class PTagDTO implements Serializable {

    private Long id;

    private String tagName;

    private Long deptId;

}
