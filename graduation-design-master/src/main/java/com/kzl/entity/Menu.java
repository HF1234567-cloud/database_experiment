package com.kzl.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

//菜单
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu {
    private String id;
    private String name;
    private String parentId;
    private Date createDate;
    private String createId;
    private Date updateDate;
    private int sort;
    private String href;
    private String state;
    private String remark;

    private List<Menu> menus;

}
