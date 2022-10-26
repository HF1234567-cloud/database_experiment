package com.kzl.controller;

import com.kzl.entity.ManageUser;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;
import com.kzl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/")
    public String redirectLogin(){
        return "login";
    }

    //不同权限登录展示的菜单不同

    //管理权限登录
    @RequestMapping("/manage/login")
    public ModelAndView login(@RequestBody ManageUser user){
        System.out.println("user:"+user);
        //判断管理账号是否能能登录账号
        ManageUser manageUser = loginService.manageLoginJudge(user);
        if(manageUser == null){
            ModelAndView modelAndView = new ModelAndView("/login");
            modelAndView.addObject("message","账号或密码错误");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/manage/getLoginData");
        modelAndView.addObject("id",manageUser.getId());
        modelAndView.addObject("loginName",manageUser.getLoginName());
        modelAndView.addObject("username",manageUser.getUsername());
        modelAndView.addObject("roleId",manageUser.getRoleId());
        modelAndView.addObject("roleName",manageUser.getRoleName());
        return modelAndView;
    }


    //学生登录
    @RequestMapping("/student/login")
    public ModelAndView studentLogin(@RequestBody Student user){
        //判断学生账号是否能能登录账号
        Student student = loginService.studentLoginJudge(user);
        if(student == null){
            ModelAndView modelAndView = new ModelAndView("/login");
            modelAndView.addObject("message","账号或密码错误");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/student/getLoginData");
        modelAndView.addObject("id",student.getId());
        modelAndView.addObject("collegeId",student.getCollegeId());
        modelAndView.addObject("loginName",student.getStudentNumber());
        modelAndView.addObject("username",student.getUsername());
        modelAndView.addObject("roleId",student.getRoleId());
        modelAndView.addObject("roleName",student.getRoleName());
        return modelAndView;
    }


    //教师登录
    @RequestMapping("/teacher/login")
    public ModelAndView teacherLogin(@RequestBody Teacher user){
        System.out.println("user:"+user);
        //判断管理账号是否能能登录账号
        Teacher teacher = loginService.teacherLoginJudge(user);
        if(teacher == null){
            ModelAndView modelAndView = new ModelAndView("/login");
            modelAndView.addObject("message","账号或密码错误");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/teacher/getLoginData");
        modelAndView.addObject("id",teacher.getId());
        modelAndView.addObject("username",teacher.getUsername());
        modelAndView.addObject("loginName",teacher.getLoginName());
        modelAndView.addObject("roleName",teacher.getRoleName());
        modelAndView.addObject("roleId",teacher.getRoleId());
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String index(HttpServletRequest request){
       request.getSession().removeAttribute("user");
       request.getSession().removeAttribute("menuList");
       return "redirect:/";
    }

}
