package com.giousa.daily.links;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class LinksInfo implements Serializable {

    private Long id;

    private String channel;

    private Integer type;

    private String title;

    private String subTitle;

    private String subText;

    private String image;

    private List<LinksUrls> links;

    private String introduction;

    private String keywords;

}
