package com.kzl.service;

import com.kzl.entity.College;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface RegisterService {

    //注册-学生
    int studentRegister(Student student);

    //查询学号时候重复
    boolean selectStudentByStudentNumber(Student student);

    //查询教师登录名称是否重复
    boolean selectTeacherByLoginName(Teacher teacher);

    //注册-教师
    int teacherRegister(Teacher teacher);

    //查询学院列表
    List<Map> selectCollegeList();
}
