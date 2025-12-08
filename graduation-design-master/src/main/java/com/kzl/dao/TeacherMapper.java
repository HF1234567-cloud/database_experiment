package com.kzl.dao;

import com.kzl.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<Course> selectCourseListByTeacher(String teacherId, String academicYear);

    List<StudentCourseRel> selectStudentListByCourse(String academicYear, String teacherId);

    boolean updateStudentCourseRel(StudentCourseRel studentCourseRel);

    List<CourseAcademicYear> selectCourseAcademicYearList();

    List<TeacherStatis> selectTeacherStatisList(String teacherId);
}
