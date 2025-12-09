package com.kzl.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

//资讯
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Information {
    private String id;
    private String title;
    private String content;
    private Date publishDate;
    @JsonAlias({"roleType"})
    private String roleId;

    private String roleName;
}
