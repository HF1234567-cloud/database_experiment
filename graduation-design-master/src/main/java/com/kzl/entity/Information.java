package com.kzl.entity;

import lombok.Data;

import java.util.Date;

//资讯
@Data
public class Information {
    private String id;
    private String title;
    private String content;
    private Date publishDate;
    private String roleId;

    private String roleName;
}
