package com.kzl.service;

import com.kzl.entity.*;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Menu> queryUserRoleMenu(String roleId);

    Information queryInformation(String roleId);

    List<Course> queryCourseList(String collegeId,String userId);

    boolean updateStudentCourseRel(StudentCourseRel studentCourseRel);

    //通过学生id查询选择的课程
    List<Course> selectCourseList(String id);

    CourseAcademicYear getCourseAcademicYear();

    //查询选课统计
    Map queryCourseSeletedCount(Student user);

}
