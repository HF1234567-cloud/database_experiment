package com.kzl.service;

import com.kzl.entity.ManageUser;
import com.kzl.entity.Menu;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;

import java.util.List;

public interface LoginService {

    //管理账号登录判断
    ManageUser manageLoginJudge(ManageUser user);

    //学生账号登录判断
    Student studentLoginJudge(Student user);

    //教师账号登录判断
    Teacher teacherLoginJudge(Teacher user);

}
