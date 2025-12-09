package com.kzl.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kzl.entity.*;
import com.kzl.service.ManageService;
import com.kzl.service.RegisterService;
import com.kzl.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("manage")
public class ManageController {

    @Autowired
    private ManageService manageService;
    @Autowired
    private RegisterService service;

    @RequestMapping("getLoginData")
    public ModelAndView getLoginData(String id,String loginName,String username,String roleId,String roleName,HttpServletRequest request){
        ManageUser user = new ManageUser(id,loginName,username,null,roleId,null,null,roleName);
        List<Menu> menuList = manageService.queryUserRoleMenu(user.getRoleId());
        Information information = manageService.queryInformation(user.getRoleId());
        if(information == null){
            information = new Information();
        }
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("information",information);
        modelAndView.addObject("userType","1");
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("menuList",menuList);
        return modelAndView;
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        return state?"index":"redirect:/";
    }

    @RequestMapping("menu")
    public ModelAndView menu(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.setViewName("manage/menu");
        return modelAndView;
    }

    //查询菜单列表数据
    @RequestMapping("menuList")
    @ResponseBody
    public JSONObject menuList(){
        JSONArray menuList = manageService.queryMenuList();
        JSONObject data = new JSONObject();
        data.put("code",0);
        data.put("data",menuList);
        return data;
    }

    //添加菜单
    @ResponseBody
    @RequestMapping("addMenu")
    public Result addMenu(@RequestBody Menu menu){
        boolean state = manageService.addMenu(menu);
        return state?Result.createSuccess("添加菜单成功"):Result.createFail("添加菜单出错");
    }

    @ResponseBody
    @RequestMapping("updateMenu")
    public Result updatedMenu(@RequestBody Menu menu){
        if(!"".equals(menu.getState()) && menu.getState()!=null){
            menu.setState(menu.getState().equals("1")?"0":"1");
        }
        boolean b = manageService.updateMenu(menu);
        return b?Result.createSuccess("修改菜单成功"):Result.createFail("修改菜单出错");
    }


    @RequestMapping("college")
    public String college(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        return state?"manage/college":"redirect:/";
    }

    //查询学院列表数据
    @RequestMapping("collegeList")
    @ResponseBody
    public JSONObject collegeList(){
        JSONArray collegeList = manageService.queryCollegeList();
        JSONObject data = new JSONObject();
        data.put("code",0);
        data.put("data",collegeList);
        return data;
    }

    //修改学院
    @ResponseBody
    @RequestMapping("updateCollege")
    public Result updateCollege(@RequestBody College college){
        if(college.getState()!=null && !"".equals(college.getState())){
            college.setState(college.getState().equals("1")?"0":"1");
        }
        boolean b = manageService.updateCollege(college);
        return b?Result.createSuccess("修改学院信息成功"):Result.createFail("修改学院信息出错");
    }

    //添加学院
    @ResponseBody
    @RequestMapping("addCollege")
    public Result addCollege(@RequestBody College college){
        boolean b = manageService.addCollege(college);
        return b?Result.createSuccess("添加学院信息成功"):Result.createFail("添加学院信息出错");
    }

    //资讯列表页面
    @RequestMapping("information")
    public String information(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        return state?"manage/informationList":"redirect:/";
    }

    //资讯列表数据
    @RequestMapping("informationList")
    @ResponseBody
    public JSONObject informationList(){
        JSONArray informationList = manageService.queryInformationList();
        JSONObject data = new JSONObject();
        data.put("code",0);
        data.put("data",informationList);
        return data;
    }

    //资讯详情
    @RequestMapping("informationDetail")
    public ModelAndView addInformation(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        ModelAndView modelAndView = new ModelAndView();
        if(!state){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        //通过id查询出具体的数据
        String id = request.getParameter("id");
        modelAndView.setViewName("manage/informationDetail");
        Information information;
        if(StringUtils.hasText(id)){
            information = manageService.queryInformationById(id);
            if(information == null){
                information = new Information();
            }
        }else{
            information = new Information();
        }
        modelAndView.addObject("information",information);
        return modelAndView;
    }

    //修改首页资讯数据
    @ResponseBody
    @RequestMapping("updateInformation")
    public Result updateInformation(@RequestBody Information information){
        boolean b = manageService.updateInformation(information);
        return b?Result.createSuccess("修改资讯信息成功"):Result.createFail("修改资讯信息失败");
    }

    //添加资讯信息
    @ResponseBody
    @RequestMapping("addInformation")
    public Result addInformation(@RequestBody Information information, HttpServletRequest request){
        if(information == null || !StringUtils.hasText(information.getTitle()) || !StringUtils.hasText(information.getContent())){
            return Result.createFail("请填写完整的资讯标题和内容");
        }
        if(!StringUtils.hasText(information.getRoleId())){
            ManageUser user = (ManageUser) request.getSession().getAttribute("user");
            if(user != null && StringUtils.hasText(user.getRoleId())){
                information.setRoleId(user.getRoleId());
            }
        }
        if(!StringUtils.hasText(information.getRoleId())){
            return Result.createFail("请选择资讯展示端");
        }
        boolean b = manageService.addInformation(information);
        return b?Result.createSuccess("添加资讯信息成功"):Result.createFail("添加资讯信息失败");
    }

    //删除资讯信息
    @ResponseBody
    @RequestMapping("deleteInformation")
    public Result deleteInformation(HttpServletRequest request){
        String id = request.getParameter("id");
        if(!StringUtils.hasText(id)){
            return Result.createFail("参数id不能为空");
        }
        boolean b = manageService.deleteInformation(id);
        return b?Result.createSuccess("删除资讯信息成功"):Result.createFail("删除资讯信息失败");
    }

    //教师管理页面
    @RequestMapping("teacher")
    public ModelAndView teacher(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
//        return state?"/manage/teacher":"redirect:/";

        ModelAndView modelAndView = new ModelAndView();
        List<Map> colleges = service.selectCollegeList();
        modelAndView.setViewName(state?"manage/teacher":"redirect:/");
        modelAndView.addObject("collegeList",colleges);
        return modelAndView;
    }

    //查询教师列表
    @ResponseBody
    @RequestMapping("teacherList")
    public Result teacherList(HttpServletRequest request){
        Teacher teacher = new Teacher();
        teacher.setUsername(request.getParameter("username"));
        teacher.setCollegeId(request.getParameter("collegeId"));
        List<Teacher> teachers = manageService.queryTeacherList(teacher);
        return Result.create(0,"",teachers);
    }

    //修改教师信息
    @ResponseBody
    @RequestMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean b = manageService.updateTeacherList(teacher);
        return b?Result.createSuccess("修改教师数据成功"):Result.createFail("修改教师数据失败");
    }


    //跳转学生管理页面
    @RequestMapping("student")
    public ModelAndView student(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
//        return state?"/manage/student":"redirect:/";

        ModelAndView modelAndView = new ModelAndView();
        List<Map> colleges = service.selectCollegeList();
        // 这里之前错误地指向了 manage/teacher，导致学生管理页面加载了教师管理模板
        modelAndView.setViewName(state?"manage/student":"redirect:/");
        modelAndView.addObject("collegeList",colleges);
        return modelAndView;
    }


    //查询学生列表
    @ResponseBody
    @RequestMapping("studentList")
    public Result studentList(HttpServletRequest request){
        Student student = new Student();
        student.setUsername(request.getParameter("username"));
        student.setCollegeId(request.getParameter("collegeId"));
        List<Student> teachers = manageService.queryStudentList(student);
        return Result.create(0,"",teachers);
    }

    //修改学生数据
    @ResponseBody
    @RequestMapping("updateStudent")
    public Result updateStudent(@RequestBody Student student){
        boolean b = manageService.updateStudentList(student);
        return b?Result.createSuccess("修改学生数据成功"):Result.createFail("修改学生数据失败");
    }

    //跳转角色管理
    @RequestMapping("role")
    public String role(HttpServletRequest request){
        boolean state = judgeUserLoginState(request);
        return state?"manage/role":"redirect:/";
    }

    //查询菜单列表
    @ResponseBody
    @RequestMapping("getMenuList")
    public Result getRoleMenuRelList(HttpServletRequest request){
        String id = request.getParameter("id");
        JSONArray menuList = manageService.queryMenuList2(id);
        return Result.createSuccess(menuList);
    }

    //角色列表
    @ResponseBody
    @RequestMapping("roleList")
    public Result roleList(HttpServletRequest request){
        List<Role> roles = manageService.queryRoleList();
        return Result.create(0,"",roles);
    }

    //修改角色
    @ResponseBody
    @RequestMapping("updateRole")
    public Result updateRole(@RequestBody Role role, HttpServletRequest request){
        boolean b = manageService.updateRole(role);
        if(b){
            // 如果修改的是当前登录用户所属的角色，则刷新session中的菜单列表
            ManageUser user = (ManageUser) request.getSession().getAttribute("user");
            if(user != null && role.getId() != null && role.getId().equals(user.getRoleId())){
                List<Menu> menuList = manageService.queryUserRoleMenu(user.getRoleId());
                request.getSession().setAttribute("menuList", menuList);
            }
        }
        return b?Result.createSuccess("修改角色数据成功"):Result.createFail("修改角色数据失败");
    }

    //添加角色
    @ResponseBody
    @RequestMapping("addRole")
    public Result addRole(@RequestBody Role role){
        boolean b = manageService.addRole(role);
        return b?Result.createSuccess("添加角色数据成功"):Result.createFail("添加角色数据失败");
    }


    public static boolean judgeUserLoginState(HttpServletRequest request){
        ManageUser user = (ManageUser) request.getSession().getAttribute("user");
        List<Menu> menus = (List) request.getSession().getAttribute("menuList");
        if(user == null || menus == null || menus.size() == 0){
            return false;
        }
        return true;
    }

}
