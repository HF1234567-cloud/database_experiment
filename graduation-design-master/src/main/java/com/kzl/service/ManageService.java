package com.kzl.service;

import com.alibaba.fastjson.JSONArray;
import com.kzl.entity.*;

import java.util.List;
import java.util.Map;

public interface ManageService {

    //查询角色对应的菜单
    List<Menu> queryUserRoleMenu(String roleId);

    //查询资讯-通过角色
    Information queryInformation(String roleId);

    //查询菜单列表
    JSONArray queryMenuList();

    //添加菜单
    boolean addMenu(Menu menu);

    //修改菜单
    boolean updateMenu(Menu menu);

    //修改学院
    boolean updateCollege(College college);

    //添加学院
    boolean addCollege(College college);

    //查询学院列表
    JSONArray queryCollegeList();

    //查询资讯列表
    JSONArray queryInformationList();

    //查询资讯信息
    Information queryInformationById(String id);

    //修改首页资讯信息
    boolean updateInformation(Information information);

    //添加首页资讯信息
    boolean addInformation(Information information);

    //查询教师信息列表
    List<Teacher> queryTeacherList(Teacher teacher);

    //修改教师信息
    boolean updateTeacherList(Teacher teacher);

    //查询学生信息列表
    List<Student> queryStudentList(Student student);

    //修改学生信息
    boolean updateStudentList(Student student);

    //查询角色关联的菜单列表
    JSONArray queryMenuList2(String roleId);

    //查询角色列表
    List<Role> queryRoleList();

    //修改角色
    boolean updateRole(Role role);

    //添加角色
    boolean addRole(Role role);
}
