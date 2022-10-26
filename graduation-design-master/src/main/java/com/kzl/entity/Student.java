package com.kzl.entity;

import lombok.Data;

import java.util.Date;

//学生
@Data
public class Student {
    private String id;
    private String studentNumber;   //学号
    private String username;        //姓名
    private String password;        //密码
    private String phone;           //手机号
    private String email;           //邮箱
    private String roleId;          //角色id
    private String collegeId;       //学院id
    private Date createDate;        //创建时间
    private String state;           //状态

    private String collegeName;     //学院名称
    private String roleName;        //角色名称

    public Student() {
    }

    public Student(String id, String studentNumber, String username, String roleId, String roleName, String collegeId) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.username = username;
        this.roleId = roleId;
        this.roleName = roleName;
        this.collegeId = collegeId;
    }
}
