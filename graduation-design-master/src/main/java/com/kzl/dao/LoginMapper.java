package com.kzl.dao;

import com.kzl.entity.ManageUser;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    ManageUser selectManageUser(ManageUser user);

    Student selectStudent(Student user);

    Teacher selectTeacher(Teacher user);
}
