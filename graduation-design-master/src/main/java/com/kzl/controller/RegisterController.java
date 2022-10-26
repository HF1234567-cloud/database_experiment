package com.kzl.controller;

import com.alibaba.fastjson.JSONObject;
import com.kzl.entity.*;
import com.kzl.service.CourseService;
import com.kzl.service.RegisterService;
import com.kzl.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService service;

    //重定向到注册页面
    @RequestMapping("/register")
    public ModelAndView redirectRegister(String type){
        ModelAndView modelAndView = new ModelAndView();
        List<Map> colleges = service.selectCollegeList();
        if(type==null){
            type = "1";
        }
        modelAndView.setViewName("register");
        modelAndView.addObject("type",type);
        modelAndView.addObject("collegeList",colleges);
        return modelAndView;
    }

    //学生注册
    @ResponseBody
    @RequestMapping("studentRegister")
    public Result studentRegister(@RequestBody Student student){
        //判断学号重复
        boolean b = service.selectStudentByStudentNumber(student);
        if(b){
            return Result.createFail("当前学号已注册,你可以通过手机号或者邮箱找回账号");
        }
        int n = service.studentRegister(student);
        return n>0?Result.createSuccess():Result.createFail();
    }

    //教师注册
    @ResponseBody
    @RequestMapping("teacherRegister")
    public Result teacherRegister(@RequestBody Teacher teacher){
        //判断注册登录账号
        boolean b = service.selectTeacherByLoginName(teacher);
        if(b){
            return Result.createFail("当前登录名已使用请换个名称注册");
        }
        int n = service.teacherRegister(teacher);
        return n>0?Result.createSuccess():Result.createFail();
    }
}
