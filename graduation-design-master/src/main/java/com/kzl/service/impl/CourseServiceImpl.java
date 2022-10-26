package com.kzl.service.impl;

import com.kzl.dao.CourseMapper;
import com.kzl.entity.Course;
import com.kzl.entity.CourseAcademicYear;
import com.kzl.entity.Student;
import com.kzl.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> queryCourseList(Course course) {
        List<Course> courses = courseMapper.selectCourseList(course);
        return courses;
    }

    @Override
    public boolean updateCourseList(Course course) {
       boolean b = courseMapper.updateCourse(course);
       return b;
    }

    @Override
    public boolean addCourseList(Course course) {
        course.setId(UUID.randomUUID().toString().replaceAll("-",""));
        course.setSelected(0);
        //获取当前学年
        CourseAcademicYear courseAcademicYear = courseMapper.selectCurrentCourseAcademicYear();
        course.setAcademicYear(courseAcademicYear.getAcademicYear());
        course.setState("1");
        return courseMapper.insertCourse(course);
    }

    @Override
    public List<CourseAcademicYear> queryCourseAcademicYearList() {
        List<CourseAcademicYear> courseAcademicYears = courseMapper.selectCourseAcademicYearList();
        return courseAcademicYears;
    }

    @Override
    public boolean updateAcademicYears(CourseAcademicYear courseAcademicYear) {
        courseMapper.updateAcademicYears();
        boolean b = courseMapper.updateAcademicYearsById(courseAcademicYear);
        return b;
    }

    @Override
    public boolean addAcademicYears(CourseAcademicYear courseAcademicYear) {
        courseAcademicYear.setId(UUID.randomUUID().toString().replaceAll("-",""));
        courseAcademicYear.setState("0");
        boolean b = courseMapper.insertAcademicYears(courseAcademicYear);
        return b;
    }

    @Override
    public boolean queryCourseEndDate(Course course) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        course.setEndDate(simpleDateFormat.format(new Date()));
        List list = courseMapper.selectCourseByEndDate(course);
        return list.size()>0?true:false;
    }

    @Override
    public List<Map> selectTeacherList() {
        List<Map> teacherList = courseMapper.selectTeacherList();
        return teacherList;
    }

}
