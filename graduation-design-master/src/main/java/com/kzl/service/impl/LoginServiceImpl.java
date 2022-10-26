package com.kzl.service.impl;

import com.kzl.dao.LoginMapper;
import com.kzl.entity.ManageUser;
import com.kzl.entity.Role;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;
import com.kzl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public ManageUser manageLoginJudge(ManageUser user) {
        ManageUser manageUser = loginMapper.selectManageUser(user);
        return manageUser;
    }

    @Override
    public Student studentLoginJudge(Student user) {
        Student student = loginMapper.selectStudent(user);
        return student;
    }

    @Override
    public Teacher teacherLoginJudge(Teacher user) {
        Teacher teacher= loginMapper.selectTeacher(user);
        return teacher;
    }
}
