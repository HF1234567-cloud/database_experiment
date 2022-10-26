package com.kzl.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

//角色信息
@Data
public class Role {
    private String id;
    private String name;
    private Date createDate;
    private String state;

    private List<Menu> menus;
}
