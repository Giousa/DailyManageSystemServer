package com.giousa.daily.links;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinksUrls implements Serializable {

    private String platform;

    private String url;
}