package com.kzl.controller;

import com.kzl.entity.ManageUser;
import com.kzl.entity.Student;
import com.kzl.entity.Teacher;
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
import java.util.Map;

@Controller
@RequestMapping("common")
public class CommonController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ManageService manageService;

    // 基本资料页面
    @RequestMapping("profile")
    public ModelAndView profile(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object user = request.getSession().getAttribute("user");
        
        if (user == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        String userType = "";
        Object userInfo = null;

        if (user instanceof Student) {
            Student student = (Student) user;
            userType = "3";
            userInfo = studentService.getStudentById(student.getId());
        } else if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            userType = "2";
            userInfo = teacherService.getTeacherById(teacher.getId());
        } else if (user instanceof ManageUser) {
            ManageUser manageUser = (ManageUser) user;
            userType = "1";
            userInfo = manageService.getManageUserById(manageUser.getId());
        }

        modelAndView.setViewName("common/profile");
        modelAndView.addObject("userInfo", userInfo);
        modelAndView.addObject("userType", userType);
        return modelAndView;
    }

    // 安全设置页面
    @RequestMapping("security")
    public ModelAndView security(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Object user = request.getSession().getAttribute("user");
        
        if (user == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        String userId = "";
        String userType = "";

        if (user instanceof Student) {
            Student student = (Student) user;
            userId = student.getId();
            userType = "3";
        } else if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            userId = teacher.getId();
            userType = "2";
        } else if (user instanceof ManageUser) {
            ManageUser manageUser = (ManageUser) user;
            userId = manageUser.getId();
            userType = "1";
        }

        modelAndView.setViewName("common/security");
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("userType", userType);
        return modelAndView;
    }

    // 更新基本资料
    @ResponseBody
    @RequestMapping("updateProfile")
    public Result updateProfile(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        try {
            String userType = params.get("userType").toString();
            @SuppressWarnings("unchecked")
            Map<String, String> userInfo = (Map<String, String>) params.get("userInfo");

            boolean success = false;

            if ("3".equals(userType)) {
                // 学生
                Student student = new Student();
                student.setId(userInfo.get("id"));
                student.setUsername(userInfo.get("username"));
                student.setPhone(userInfo.get("phone"));
                student.setEmail(userInfo.get("email"));
                success = studentService.updateStudentProfile(student);
                
                if (success) {
                    // 更新session
                    Student sessionStudent = (Student) request.getSession().getAttribute("user");
                    sessionStudent.setUsername(userInfo.get("username"));
                    sessionStudent.setPhone(userInfo.get("phone"));
                    sessionStudent.setEmail(userInfo.get("email"));
                }
            } else if ("2".equals(userType)) {
                // 教师
                Teacher teacher = new Teacher();
                teacher.setId(userInfo.get("id"));
                teacher.setUsername(userInfo.get("username"));
                teacher.setPhone(userInfo.get("phone"));
                teacher.setEmail(userInfo.get("email"));
                success = teacherService.updateTeacherProfile(teacher);
                
                if (success) {
                    // 更新session
                    Teacher sessionTeacher = (Teacher) request.getSession().getAttribute("user");
                    sessionTeacher.setUsername(userInfo.get("username"));
                    sessionTeacher.setPhone(userInfo.get("phone"));
                    sessionTeacher.setEmail(userInfo.get("email"));
                }
            } else if ("1".equals(userType)) {
                // 管理员
                ManageUser manageUser = new ManageUser();
                manageUser.setId(userInfo.get("id"));
                manageUser.setUsername(userInfo.get("username"));
                success = manageService.updateManageUserProfile(manageUser);
                
                if (success) {
                    // 更新session
                    ManageUser sessionManage = (ManageUser) request.getSession().getAttribute("user");
                    sessionManage.setUsername(userInfo.get("username"));
                }
            }

            return success ? Result.createSuccess("资料修改成功") : Result.createFail("资料修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.createFail("系统异常：" + e.getMessage());
        }
    }

    // 修改密码
    @ResponseBody
    @RequestMapping("updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> params, HttpServletRequest request) {
        try {
            String userType = params.get("userType");
            String id = params.get("id");
            String oldPassword = params.get("oldPassword");
            String newPassword = params.get("newPassword");

            boolean success = false;

            if ("3".equals(userType)) {
                success = studentService.updatePassword(id, oldPassword, newPassword);
            } else if ("2".equals(userType)) {
                success = teacherService.updatePassword(id, oldPassword, newPassword);
            } else if ("1".equals(userType)) {
                success = manageService.updatePassword(id, oldPassword, newPassword);
            }

            return success ? Result.createSuccess("密码修改成功，请重新登录") : Result.createFail("密码修改失败，请检查原密码是否正确");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.createFail("系统异常：" + e.getMessage());
        }
    }
}
