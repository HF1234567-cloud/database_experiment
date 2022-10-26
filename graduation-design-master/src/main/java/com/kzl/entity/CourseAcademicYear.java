package com.kzl.entity;

import lombok.Data;

//课程学年信息
@Data
public class CourseAcademicYear {
    private String id;                  //主键
    private String academicYear;        //学年
    private String state;               //是否选中

    private String academicYearName;    //学年名称
    private String type;                //是否选中
}
