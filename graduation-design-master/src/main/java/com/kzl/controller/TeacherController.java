package com.kzl.controller;

import com.kzl.entity.*;
import com.kzl.service.ManageService;
import com.kzl.service.StudentService;
import com.kzl.service.TeacherService;
import com.kzl.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import jakarta.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    //登录获取数据
    @RequestMapping("getLoginData")
    public ModelAndView getLoginData(String id, String loginName, String username, String roleId, String roleName, HttpServletRequest request){
        Teacher user = new Teacher(id,loginName,username,roleId,roleName);
        List<Menu> menuList = teacherService.queryUserRoleMenu(user.getRoleId());
        Information information = teacherService.queryInformation(user.getRoleId());
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("information",information);
        modelAndView.addObject("userType","2");
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("menuList",menuList);
        return modelAndView;
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        return state?"index":"redirect:/";
    }

    //课程信息
    @RequestMapping("courseInfo")
    public ModelAndView courseInfo(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Teacher user = (Teacher) request.getSession().getAttribute("user");
        List<Course> courses = teacherService.selectCourseListByTeacherId(user.getId());
        CourseAcademicYear courseAcademicYear = studentService.getCourseAcademicYear();
        modelAndView.setViewName("teacher/courseInfo");
        modelAndView.addObject("courses",courses);
        modelAndView.addObject("academicYear",courseAcademicYear.getAcademicYearName());
        return modelAndView;
    }


    //成绩管理
    @RequestMapping("scoreInfo")
    public ModelAndView scoreInfo(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Teacher user = (Teacher) request.getSession().getAttribute("user");
        CourseAcademicYear currentYear = studentService.getCourseAcademicYear();
        List<CourseAcademicYear> academicYears = teacherService.selectCourseYearList(currentYear.getId());
        if (academicYears == null || academicYears.isEmpty()) {
            // 如果学年列表为空，至少加入当前学年，避免下拉框为空
            academicYears = new java.util.ArrayList<>();
            academicYears.add(currentYear);
        }
        List<Course> courses = teacherService.selectCourseListByTeacherId(user.getId());

        modelAndView.setViewName("teacher/scoreInfo");
        modelAndView.addObject("academicYears", academicYears);
        modelAndView.addObject("courses", courses);
        return modelAndView;
    }

    //教师关联课程下所有的学生信息查询
    @ResponseBody
    @RequestMapping("courseList")
    public Result courseList(HttpServletRequest request, String academicYear, String courseId, String studentName){
        Teacher user = (Teacher) request.getSession().getAttribute("user");
        if (academicYear == null || academicYear.isEmpty() || "请选择学年".equals(academicYear)) {
             CourseAcademicYear courseAcademicYear = studentService.getCourseAcademicYear();
             academicYear = courseAcademicYear.getAcademicYear();
        }
        List<StudentCourseRel> courses = teacherService.queryStudentList(academicYear,user.getId(), courseId, studentName);
        return Result.create(0,"",courses);
    }

    //修改学生成绩
    @ResponseBody
    @RequestMapping("updateScore")
    public Result updateScore(@RequestBody StudentCourseRel studentCourseRel, HttpServletRequest request){
        //id  是否合格、评分备注
        // 修改状态 、评价教师教师、评价状态、
        Teacher user = (Teacher) request.getSession().getAttribute("user");
        boolean b = teacherService.updateStudentScore(studentCourseRel,user.getId());
        return b?Result.createSuccess("成绩评价成功"):Result.createFail("成绩评价修改失败");
    }


    //跳转选课学生信息页面
    @RequestMapping("selectedCourseStu")
    public ModelAndView selectedCourseStu(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Teacher user = (Teacher) request.getSession().getAttribute("user");
        CourseAcademicYear courseAcademicYear = studentService.getCourseAcademicYear();
        List<CourseAcademicYear> courseAcademicYears = teacherService.selectCourseYearList(courseAcademicYear.getId());
        if (courseAcademicYears == null || courseAcademicYears.isEmpty()) {
            // 如果学年列表为空，至少加入当前学年，避免下拉框为空
            courseAcademicYears = new java.util.ArrayList<>();
            courseAcademicYears.add(courseAcademicYear);
        }
        List<Course> courses = teacherService.selectCourseList(user.getId(), courseAcademicYear.getAcademicYear());
        modelAndView.setViewName("teacher/selectedCourseStu");
        modelAndView.addObject("courseAcademicYears",courseAcademicYears);
        modelAndView.addObject("courses",courses);
        return modelAndView;
    }

    //选修课程的学生信息查询
    @ResponseBody
    @RequestMapping("studentInfoInCourse")
    public Result studentInfoInCourse(HttpServletRequest request, String academicYear, String courseId, String studentName){
        Teacher user = (Teacher) request.getSession().getAttribute("user");
        if (academicYear == null || academicYear.isEmpty() || "请选择学年".equals(academicYear)) {
             CourseAcademicYear courseAcademicYear = studentService.getCourseAcademicYear();
             academicYear = courseAcademicYear.getAcademicYear();
        }
        List<StudentCourseRel> studentCourses = teacherService.getStudentInCourse(academicYear,user.getId(), courseId, studentName);
        return Result.create(0,"",studentCourses);
    }



    //统计信息
    // @RequestMapping("statisticalInfo")
    // public String statisticalInfo(HttpServletRequest request){
    //     boolean state = judgeUserLoginState(request);
    //     return state?"teacher/statisticalInfo":"redirect:/";
    // }
    @RequestMapping("statisticalInfo")
    public ModelAndView statisticalInfo(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if (state ==  true)
        {
            Teacher user = (Teacher) request.getSession().getAttribute("user");
            CourseAcademicYear currentYear = studentService.getCourseAcademicYear();
            List<TeacherStatis> CoutStudent_collage = teacherService.selectTeacherStatisList(user.getId(), currentYear.getAcademicYear());
            List<TeacherStatis> courseCountList = teacherService.selectCourseCountList(user.getId(), currentYear.getAcademicYear());
            modelAndView.setViewName("teacher/statisticalInfo");
            modelAndView.addObject("TeacherStatis", CoutStudent_collage);
            modelAndView.addObject("CourseStatis", courseCountList);
        }else {
            modelAndView.setViewName("redirect:/");
        }
        return  modelAndView;
    }









    private boolean judgeUserLoginState(HttpServletRequest request){
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        List<Menu> menus = (List) request.getSession().getAttribute("menuList");
        if(teacher == null || menus == null || menus.size() == 0){
            return false;
        }
        return true;
    }
}
