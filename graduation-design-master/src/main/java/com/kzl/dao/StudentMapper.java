package com.kzl.dao;

import com.kzl.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Course> selectCourseList(String collegeId,String courseAcademicYear);

    StudentCourseRel selectStudentCourseRel(String courseId, String studentId);

    boolean insertStudentCourseRel(StudentCourseRel studentCourseRel);

    boolean deleteStudentCourseRel(StudentCourseRel studentCourseRel);

    List<Course> selectCourseListByStudent(String studentId,String courseAcademicYear);

    CourseAcademicYear selectCourseAcademicYearByState();

    Map selectCompleteCourseCount(Student user);

    Map selectUnfinishedCourseCount(Student user);

    Map selectSelectedCourseCount(Student user);

    Course selectCourseByPeriodTime(String courseId, Date nowTime);

    Course selectCourseById(String courseId);

    int updateCourse(Course course2);
}
