package com.kzl.entity;

import lombok.Data;

import java.util.Date;

//教师
@Data
public class Teacher {
    private String id;
    private String loginName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String roleId;
    private String collegeId;
    private Date createDate;
    private String state;

    private String collegeName;
    private String roleName;

    public Teacher() {
    }

    public Teacher(String id, String loginName, String username, String roleId, String roleName) {
        this.id = id;
        this.loginName = loginName;
        this.username = username;
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
