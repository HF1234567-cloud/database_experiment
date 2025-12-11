package com.kzl.controller;

import com.kzl.entity.*;
import com.kzl.service.StudentService;
import com.kzl.service.TeacherService;
import com.kzl.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
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
        try {
            boolean success = studentService.updateStudentCourseRel(studentCourseRel);
            if(!success){
                return buildFailResult("当前课程无法选择，选修时间已过或人数已满");
            }
            return Result.createSuccess("选课成功");
        } catch (IllegalStateException ex) {
            return buildFailResult(ex.getMessage());
        } catch (DataAccessException ex) {
            String rootMessage = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
            return buildFailResult(resolvePermissionMessage(rootMessage));
        } catch (Exception ex) {
            return buildFailResult("选课失败，请稍后再试");
        }
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

    private String resolvePermissionMessage(String detail){
        if(detail == null){
            return "选课失败，请联系管理员";
        }
        if(detail.contains("STUDENT_DISABLED")){
            return "该学生已被禁用，请联系管理员获取权限";
        }
        if(detail.contains("TEACHER_DISABLED")){
            return "授课教师已被禁用，请联系管理员获取权限";
        }
        if(detail.contains("STUDENT_NOT_FOUND")){
            return "未找到学生信息，请联系管理员获取权限";
        }
        if(detail.contains("TEACHER_NOT_FOUND")){
            return "未找到授课教师，请联系管理员获取权限";
        }
        return detail;
    }

    private Result buildFailResult(String message){
        Result result = Result.createFail(message);
        result.setData(message,0);
        return result;
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

