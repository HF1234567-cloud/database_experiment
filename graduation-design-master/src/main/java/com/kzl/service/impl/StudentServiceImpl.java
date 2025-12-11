package com.kzl.service.impl;

import com.kzl.dao.ManageMapper;
import com.kzl.dao.StudentMapper;
import com.kzl.entity.*;
import com.kzl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public List<Menu> queryUserRoleMenu(String roleId) {
        List<Menu> menus = manageMapper.selectFirstMenuByRoleId(roleId,"0");
        for(Menu menu:menus){
            List<Menu> menuSecond = manageMapper.selectFirstMenuByRoleId(roleId,menu.getId());
            menu.setMenus(menuSecond);
        }
        return menus;
    }

    @Override
    public Information queryInformation(String roleId) {
        Information information = manageMapper.selectInformationByRoleId(roleId);
        return information;
    }

    @Override
    public List<Course> queryCourseList(String collegeId,String userId) {
        CourseAcademicYear courseAcademicYear = studentMapper.selectCourseAcademicYearByState();
        List<Course> courses = studentMapper.selectCourseList(collegeId,courseAcademicYear.getAcademicYear());
        for(Course course:courses){
            //查询当前课程是否选择
            StudentCourseRel studentCourseRel = studentMapper.selectStudentCourseRel(course.getId(),userId);
            course.setType(studentCourseRel==null?"0":"1");
        }
        return courses;
    }

    @Override
    public boolean updateStudentCourseRel(StudentCourseRel studentCourseRel) {
        validateStudentAndTeacherState(studentCourseRel);
        //查询当前时间是否可以选择课程
        Course course = studentMapper.selectCourseByPeriodTime(studentCourseRel.getCourseId(),new Date());
        if(course == null){
            return false;
        }
        //查询可选人数和已选人数
        Course course1 = studentMapper.selectCourseById(studentCourseRel.getCourseId());
        if(course1.getOptional() == course1.getSelected()){
            return false;
        }
        boolean b = false;
        Course course2 = new Course();
        course2.setId(studentCourseRel.getCourseId());
        if("0".equals(studentCourseRel.getType())){
            studentCourseRel.setId(UUID.randomUUID().toString().replaceAll("-",""));
            studentCourseRel.setState("0");
            b = studentMapper.insertStudentCourseRel(studentCourseRel);
            course2.setUseNumber(1);
            //添加已选人说

        }else{
            b = studentMapper.deleteStudentCourseRel(studentCourseRel);
            //删除已选人数
            course2.setUseNumber(-1);
        }
        studentMapper.updateCourse(course2);
        return b;
    }


    @Override
    public List<Course> selectCourseList(String id) {
        CourseAcademicYear courseAcademicYear = studentMapper.selectCourseAcademicYearByState();
        List<Course> courses = studentMapper.selectCourseListByStudent(id,courseAcademicYear.getAcademicYear());
        return courses;
    }

    @Override
    public CourseAcademicYear getCourseAcademicYear() {
        CourseAcademicYear courseAcademicYear = studentMapper.selectCourseAcademicYearByState();
        String yearName = courseAcademicYear.getAcademicYear().substring(0,4);
        String monthName = courseAcademicYear.getAcademicYear().substring(4);
//        String academicYearName = "02".equals(monthName)?yearName+"年上半学年":yearName+"年下半学年";
        courseAcademicYear.setAcademicYearName(courseAcademicYear.getAcademicYear());
        return courseAcademicYear;
    }

    @Override
    public Map queryCourseSeletedCount(Student user) {
        Map<String,Object> data = new HashMap<>();
        //已完成课程统计
        Map map = studentMapper.selectCompleteCourseCount(user);
        data.put("complete",map);
        //未完成课程统计
        Map map2 = studentMapper.selectUnfinishedCourseCount(user);
        data.put("unfinished",map2);
        //已选课程统计
        Map map3 = studentMapper.selectSelectedCourseCount(user);
        data.put("selected",map3);
        return data;
    }

    @Override
    public Student getStudentById(String id) {
        return studentMapper.selectStudentById(id);
    }

    @Override
    public boolean updateStudentProfile(Student student) {
        return studentMapper.updateStudentProfile(student) > 0;
    }

    @Override
    public boolean updatePassword(String id, String oldPassword, String newPassword) {
        Student student = studentMapper.selectStudentByIdAndPassword(id, oldPassword);
        if (student == null) {
            return false;
        }
        return studentMapper.updateStudentPassword(id, newPassword) > 0;
    }

    private void validateStudentAndTeacherState(StudentCourseRel studentCourseRel) {
        String studentState = studentMapper.selectStudentState(studentCourseRel.getStudentId());
        if(studentState == null){
            throw new IllegalStateException("未找到学生信息，请联系管理员获取权限");
        }
        if(!"1".equals(studentState)){
            throw new IllegalStateException("该学生已被禁用，请联系管理员获取权限");
        }
        String teacherState = studentMapper.selectTeacherStateByCourseId(studentCourseRel.getCourseId());
        if(teacherState != null && !"1".equals(teacherState)){
            throw new IllegalStateException("授课教师已被禁用，请联系管理员获取权限");
        }
    }
}

