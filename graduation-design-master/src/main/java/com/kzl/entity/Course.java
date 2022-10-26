package com.kzl.entity;

import lombok.Data;


//课程
@Data
public class Course {
    private String id;
    private String courseName;      //课程名称
    private String collegeId;       //学院id
    private String teacherId;       //教师id
    private String classPlace;      //上课地点
    private String classDate;       //上课时间
    private String startDate;       //报考开始时间
    private String endDate;         //报考结束时间
    private double credits;         //学分
    private String academicYear;    //学年
    private String teachEndDate;    //课程结束时间   课程结束之后教师可以对选择这门课程的学生打分
    private int optional;           //可选人数
    private int primaryAmount;      //预选人数
    private int selected;           //选中人数
    private String remark;          //课程说明
    private String state;           //删除标识

    private String collegeName;
    private String teacherName;
    private String type;            //课程是否选择
    private int useNumber;

}
