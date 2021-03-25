package com.giousa.daily.json;

import lombok.Data;

import java.io.Serializable;

@Data
public class CardLinksInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String platform;

    private String url;

}
