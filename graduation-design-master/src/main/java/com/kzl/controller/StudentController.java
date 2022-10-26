package com.kzl.controller;

import com.kzl.entity.*;
import com.kzl.service.StudentService;
import com.kzl.service.TeacherService;
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
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //登录获取数据
    @RequestMapping("getLoginData")
    public ModelAndView getLoginData(String id, String loginName, String username, String roleId, String roleName,String collegeId, HttpServletRequest request){
        Student user = new Student(id,loginName,username,roleId,roleName,collegeId);
        List<Menu> menuList = studentService.queryUserRoleMenu(user.getRoleId());
        Information information = studentService.queryInformation(user.getRoleId());
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("information",information);
        modelAndView.addObject("userType","3");
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("menuList",menuList);
        return modelAndView;
    }

//    //跳转首页
//    @RequestMapping("index")
//    public String index(HttpServletRequest request){
//        boolean state = judgeUserLoginState(request);
//        return state?"../index":"redirect:/";
//    }

    //跳转选课中心
    @RequestMapping("courseCase")
    public ModelAndView forwardCourseCase(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(state?"student/courseCase":"redirect:/");
        return modelAndView;
    }

    //学生选课
    @ResponseBody
    @RequestMapping("courseSelection")
    public Result courseSelection(@RequestBody StudentCourseRel studentCourseRel){
        boolean b = studentService.updateStudentCourseRel(studentCourseRel);
        if(!b){
            return Result.createSuccess("当前课程无法选择!!! <br> 选修时间已过或可选人数不足");
        }
        return Result.createSuccess("选课成功");
    }

    @ResponseBody
    @RequestMapping("courseList")
    public Result courseList(HttpServletRequest request){
        Student user = (Student) request.getSession().getAttribute("user");
        List<Course> courses = studentService.queryCourseList(user.getCollegeId(),user.getId());
        return Result.create(0,"",courses);
    }

    //跳转已选课程
    @RequestMapping("selectedCourse")
    public ModelAndView selectedCourse(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Student user = (Student) request.getSession().getAttribute("user");
        List<Course> courses = studentService.selectCourseList(user.getId());
        CourseAcademicYear courseAcademicYear = studentService.getCourseAcademicYear();
        double creditsCount = 0;
        for(Course course:courses){
            creditsCount += course.getCredits();
        }
        modelAndView.addObject("courses",courses);
        modelAndView.addObject("academicYear",courseAcademicYear.getAcademicYearName());
        modelAndView.addObject("creditsCount",creditsCount);
        modelAndView.addObject("courseCount",courses.size());
        return modelAndView;
    }


    //跳转选课统计
    @RequestMapping("statistical")
    public ModelAndView courseStatistics(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Student user = (Student) request.getSession().getAttribute("user");
        Map map = studentService.queryCourseSeletedCount(user);
        modelAndView.addObject("complete",map.get("complete"));
        modelAndView.addObject("unfinished",map.get("unfinished"));
        modelAndView.addObject("selected",map.get("selected"));
        return modelAndView;
    }

    private boolean judgeUserLoginState(HttpServletRequest request){
        Student user = (Student) request.getSession().getAttribute("user");
        List<Menu> menus = (List) request.getSession().getAttribute("menuList");
        if(user == null || menus == null || menus.size() == 0){
            return false;
        }
        return true;
    }
}
