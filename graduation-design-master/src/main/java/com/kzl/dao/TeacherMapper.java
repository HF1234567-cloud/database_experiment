package com.kzl.dao;

import com.kzl.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherMapper {

    List<Course> selectCourseListByTeacher(String teacherId, String academicYear);

    List<StudentCourseRel> selectStudentListByCourse(@Param("academicYear") String academicYear, @Param("teacherId") String teacherId, @Param("courseId") String courseId, @Param("studentName") String studentName);

    boolean updateStudentCourseRel(StudentCourseRel studentCourseRel);

    List<CourseAcademicYear> selectCourseAcademicYearList();

    List<TeacherStatis> selectTeacherStatisList(String teacherId);

    List<TeacherStatis> selectCourseCountList(String teacherId);

    Teacher selectTeacherById(String id);

    int updateTeacherProfile(Teacher teacher);

    Teacher selectTeacherByIdAndPassword(String id, String password);

    int updateTeacherPassword(String id, String newPassword);
}
