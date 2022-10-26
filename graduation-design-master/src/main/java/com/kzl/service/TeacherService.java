package com.kzl.service;

import com.kzl.entity.*;

import java.util.List;

public interface TeacherService {
    List<Menu> queryUserRoleMenu(String roleId);

    Information queryInformation(String roleId);

    //查询教师课程
    List<Course> selectCourseListByTeacherId(String id);

    //查询学生的选课
    List<StudentCourseRel> queryStudentList(String academicYear, String userId);

    //学生成绩评价
    boolean updateStudentScore(StudentCourseRel studentCourseRel, String id);

    //查询选择课程的学生信息
    List<StudentCourseRel> getStudentInCourse(String academicYear, String userId);

    //查询课程
    List<Course> selectCourseList(String teacherId, String courseAcademicYear);

    //学年列表
    List<CourseAcademicYear> selectCourseYearList(String courseAcademicYearId);
}
