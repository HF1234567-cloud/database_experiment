package com.kzl.dao;

import com.kzl.entity.College;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegisterMapper {

    int insertTeacher(Teacher teacher);

    int insertStudent(Student student);

    List<Student> selectStudentByStudentNumber(Student student);

    List<Teacher> selectTeacherByLoginName(Teacher teacher);

    List<Map> selectColleageList();
}
