package com.kzl.controller;

import com.kzl.dao.ManageMapper;
import com.kzl.entity.*;
import com.kzl.service.CourseService;
import com.kzl.service.RegisterService;
import com.kzl.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private RegisterService service;

    //跳转课程列表页面
    @RequestMapping("list")
    public ModelAndView course(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        List<Map> colleges = service.selectCollegeList();
        boolean state = ManageController.judgeUserLoginState(request);
        modelAndView.setViewName(state?"manage/course":"redirect:/");
        modelAndView.addObject("collegeList",colleges);
        return modelAndView;
    }

    //查询课程列表
    @ResponseBody
    @RequestMapping("courseList")
    public Result courseList(HttpServletRequest request){
        Course course = new Course();
        course.setCourseName(request.getParameter("courseName"));
        course.setCollegeId(request.getParameter("collegeId"));
        List<Course> courses = courseService.queryCourseList(course);
        return Result.create(0,"",courses);
    }

    //课程修改
    @ResponseBody
    @RequestMapping("updateCourse")
    public Result updateCourse(@RequestBody Course course){
        //判断课程是否结束
        if(course.getState() == "0"){
            boolean status = courseService.queryCourseEndDate(course);
            if(status){
                return Result.createFail("当前课程还没有结束，无法删除");
            }
        }
        boolean b = courseService.updateCourseList(course);
        return b?Result.createSuccess("修改课程数据成功"):Result.createFail("修改课程数据失败");
    }

    @RequestMapping("forwardAdd")
    public ModelAndView forwardAdd(HttpServletRequest request){
        boolean state = ManageController.judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        List<Map> colleges = service.selectCollegeList();
        List<Map> teacherList = courseService.selectTeacherList();
        modelAndView.setViewName(state?"manage/addCourse":"redirect:/");
        modelAndView.addObject("teacherList",teacherList);
        modelAndView.addObject("collegeList",colleges);
        return modelAndView;


    }

    @ResponseBody
    @RequestMapping("addCourse")
    public Result addCourse(@RequestBody Course course){
        boolean b = courseService.addCourseList(course);
        return b?Result.createSuccess("添加课程数据成功"):Result.createFail("添加课程数据失败");
    }

    //跳转学年管理
    @RequestMapping("courseAcademicYear")
    public ModelAndView courseAcademicYear(HttpServletRequest request){
        boolean state = ManageController.judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        //查询学年数据
        List<CourseAcademicYear> courseAcademicYears = courseService.queryCourseAcademicYearList();
        modelAndView.addObject("courseAcademicYears",courseAcademicYears);
        modelAndView.setViewName("manage/courseAcademicYear");
        return modelAndView;
    }

    //修改学年
    @ResponseBody
    @RequestMapping("updateAcademicYears")
    public Result updateAcademicYears(@RequestBody CourseAcademicYear courseAcademicYear){
        boolean b = courseService.updateAcademicYears(courseAcademicYear);
        return b?Result.createSuccess("修改学年数据成功"):Result.createFail("修改学年数据失败");
    }

    //添加学年
    @ResponseBody
    @RequestMapping("addAcademicYears")
    public Result addAcademicYears(@RequestBody CourseAcademicYear courseAcademicYear){
        boolean b = courseService.addAcademicYears(courseAcademicYear);
        return b?Result.createSuccess("添加学年数据成功"):Result.createFail("添加学年数据失败");
    }


}
