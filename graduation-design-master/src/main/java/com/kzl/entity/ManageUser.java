package com.kzl.entity;

import lombok.Data;

import java.util.Date;

//系统账号
@Data
public class ManageUser {
    private String id;
    private String loginName;
    private String username;
    private String password;
    private String roleId;
    private Date createDate;
    private String state;

    private String roleName;

    public ManageUser() {
    }

    public ManageUser(String id, String loginName, String username, String password, String roleId, Date createDate, String state, String roleName) {
        this.id = id;
        this.loginName = loginName;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.createDate = createDate;
        this.state = state;
        this.roleName = roleName;
    }
}
