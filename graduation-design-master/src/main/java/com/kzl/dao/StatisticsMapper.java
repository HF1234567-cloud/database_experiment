package com.kzl.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StatisticsMapper {

    long countStudents();

    long countTeachers();

    long countColleges();

    long countCourses();

    long countInformation();

    List<Map<String,Object>> countStudentByCollege();

    List<Map<String,Object>> countTeacherByCollege();

    List<Map<String,Object>> countInformationByRole();

    /**
     * 课程按学年统计（课程数量、选课总人数）
     */
    List<Map<String,Object>> countCourseByAcademicYear();

    /**
     * 选课人数 Top N 课程列表
     */
    List<Map<String,Object>> topCourseBySelected();
}
