package com.kzl.dao;

import com.kzl.entity.Course;
import com.kzl.entity.CourseAcademicYear;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseMapper {

    List<Course> selectCourseList(Course course);

    boolean updateCourse(Course course);

    boolean insertCourse(Course course);

    List<CourseAcademicYear> selectCourseAcademicYearList();

    boolean updateAcademicYears();

    boolean updateAcademicYearsById(CourseAcademicYear courseAcademicYear);

    boolean insertAcademicYears(CourseAcademicYear courseAcademicYear);

    List<Course> selectCourseByEndDate(Course course);

    CourseAcademicYear selectCurrentCourseAcademicYear();

    List<Map> selectTeacherList();
}
