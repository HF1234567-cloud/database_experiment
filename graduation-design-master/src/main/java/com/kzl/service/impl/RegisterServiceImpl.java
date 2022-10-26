package com.kzl.service.impl;

import com.kzl.dao.RegisterMapper;
import com.kzl.entity.College;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;
import com.kzl.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public int studentRegister(Student student) {
        student.setId(UUID.randomUUID().toString().replaceAll("-",""));
        student.setCreateDate(new Date());
        student.setRoleId("3");
        student.setState("1");
        int n = registerMapper.insertStudent(student);
        return n;
    }

    @Override
    public boolean selectStudentByStudentNumber(Student student) {
        List<Student> list = registerMapper.selectStudentByStudentNumber(student);
        System.out.println(list.size());
        return list.size()>0;
    }

    @Override
    public boolean selectTeacherByLoginName(Teacher teacher) {
        List<Teacher> list = registerMapper.selectTeacherByLoginName(teacher);
        return list.size()>0;
    }

    @Override
    public int teacherRegister(Teacher teacher) {
        teacher.setId(UUID.randomUUID().toString().replaceAll("-",""));
        teacher.setCreateDate(new Date());
        teacher.setState("1");
        teacher.setRoleId("2");
        int n = registerMapper.insertTeacher(teacher);
        return n;
    }

    @Override
    public List<Map> selectCollegeList() {
        List<Map> colleges = registerMapper.selectColleageList();
        return colleges;
    }


}
