package com.kzl.entity;

import lombok.Data;

import java.util.Date;

//学生课程关联表
@Data
public class StudentCourseRel {
    private String id;
    private String studentId;       //学生id
    private String courseId;        //课程id
    private String teacherId;       //评分教师id
    private String isQualified;     //是否合格
    private String creditsRemark;   //教师评分备注
    private String state;           //是否已对当前学生评分 0未平分 1已评分 课程未结束不能评分

    private String type;
    private String studentName;     //学生姓名
    private String courseName;      //课程名称
    private String courseCredits;   //课程学分
    private String academicYear;    //学年
    private Date teachEndDate;      //课程结束时间
    private String studentNumber;   //学生学号
}
