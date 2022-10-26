package com.kzl.service;

import com.kzl.entity.Course;
import com.kzl.entity.CourseAcademicYear;
import com.kzl.entity.Student;

import java.util.List;
import java.util.Map;

public interface CourseService {

    List<Course> queryCourseList(Course course);

    boolean updateCourseList(Course course);

    boolean addCourseList(Course course);

    //查询学年列表
    List<CourseAcademicYear> queryCourseAcademicYearList();

    //修改学年
    boolean updateAcademicYears(CourseAcademicYear courseAcademicYear);

    //添加学年
    boolean addAcademicYears(CourseAcademicYear courseAcademicYear);

    //查询课程是否结束
    boolean queryCourseEndDate(Course course);

    //查询教师列表
    List<Map> selectTeacherList();
}
