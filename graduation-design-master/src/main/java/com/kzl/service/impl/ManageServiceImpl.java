package com.kzl.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kzl.dao.ManageMapper;
import com.kzl.entity.*;
import com.kzl.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ManageServiceImpl implements ManageService {

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
    public JSONArray queryMenuList() {
        JSONArray menus = manageMapper.selectMenuList();
        return menus;
    }

    @Override
    public boolean addMenu(Menu menu) {
        menu.setId(UUID.randomUUID().toString().replaceAll("-",""));
        menu.setCreateDate(new Date());
        menu.setUpdateDate(new Date());
        menu.setState("1");
        boolean b = manageMapper.insertMenu(menu);
        return b;
    }

    @Override
    public boolean updateMenu(Menu menu) {
        menu.setUpdateDate(new Date());
        boolean state = manageMapper.updateMenu(menu);
        return state;
    }

    @Override
    public boolean updateCollege(College college) {
        college.setUpdateDate(new Date());
        boolean state = manageMapper.updateCollege(college);
        return state;
    }

    @Override
    public boolean addCollege(College college) {
        college.setId(UUID.randomUUID().toString().replaceAll("-",""));
        college.setStudentNum(0);
        college.setTeacherNum(0);
        college.setUpdateDate(new Date());
        college.setCreateDate(new Date());
        college.setState("1");
        boolean b = manageMapper.insertCollege(college);
        return b;
    }

    @Override
    public JSONArray queryCollegeList() {
        JSONArray colleges = manageMapper.selectCollegeList();
        return colleges;
    }

    @Override
    public JSONArray queryInformationList() {
        JSONArray informationList = manageMapper.selectInformationList();
        return informationList;
    }

    @Override
    public Information queryInformationById(String id) {
        Information information = manageMapper.selectInformationById(id);
        return information;
    }

    @Override
    public boolean updateInformation(Information information) {
        boolean b = manageMapper.updateInformation(information);
        return b;
    }

    @Override
    public boolean addInformation(Information information) {
        information.setId(UUID.randomUUID().toString().replaceAll("-",""));
        information.setPublishDate(new Date());
        boolean b = manageMapper.addInformation(information);
        return b;
    }

    @Override
    public List<Teacher> queryTeacherList(Teacher teacher) {
        List<Teacher> teachers = manageMapper.selectTeacherList(teacher);
        return teachers;
    }

    @Override
    public boolean updateTeacherList(Teacher teacher) {
        if(teacher.getState()!=null){
            teacher.setState("1".equals(teacher.getState())?"0":"1");
        }
        boolean b = manageMapper.updateTeacher(teacher);
        return b;
    }

    @Override
    public List<Student> queryStudentList(Student student) {
        List<Student> students = manageMapper.selectStudentList(student);
        return students;
    }

    @Override
    public boolean updateStudentList(Student student) {
        if(student.getState()!=null){
            student.setState("1".equals(student.getState())?"0":"1");
        }
        boolean b = manageMapper.updateStudent(student);
        return b;
    }

    @Override
    public JSONArray queryMenuList2(String roleId) {
        JSONArray array = new JSONArray();
        //查询出当前角色关联的所有的菜单-在有角色的情况下
        List<Menu> roleRelMenu = roleId.isEmpty()?new ArrayList<>() :manageMapper.selectMenuByRoleId(roleId);
        //先查询出所有的菜单
        List<Menu> menus = manageMapper.selectAllMenuList();
        for(Menu menu : menus){
            if(menu.getParentId().equals("0")){
                JSONObject data = new JSONObject();
                data.put("id",menu.getId());
                data.put("title",menu.getName());
                //判断是否选中
                boolean state = false;
                for(Menu relMenu : roleRelMenu){
                    if(relMenu.getId().equals(menu.getId())){
                        state = true;
                        continue;
                    }
                }
                data.put("checked",state);
                data.put("spread",true);
                JSONArray secondArr = new JSONArray();
                //判断是否有下级菜单
                for(Menu secondMenu : menus){
                    if(!"0".equals(secondMenu.getParentId()) && menu.getId().equals(secondMenu.getParentId())){
                        JSONObject secondData = new JSONObject();
                        secondData.put("title",secondMenu.getName());
                        secondData.put("id",secondMenu.getId());
                        //判断是否选中
                        boolean state2 = false;
                        for(Menu relMenu : roleRelMenu){
                            if(relMenu.getId().equals(secondMenu.getId())){
                                state2 = true;
                                continue;
                            }
                        }
                        if(state2){
                            secondData.put("checked",state2);
                        }
                        secondArr.add(secondData);
                    }
                }
                data.put("children",secondArr);
                array.add(data);
            }
        }
        return array;
    }

    @Override
    public List<Role> queryRoleList() {
        List<Role> roles = manageMapper.selectRoleList();
        return roles;
    }

    @Override
    public boolean updateRole(Role role) {
        if(role.getState()!=null){
            role.setState("1".equals(role.getState())?"0":"1");
        }
        boolean b = manageMapper.updateRole(role);
        if(role.getMenus()!=null && role.getMenus().size()>0){
            int n = manageMapper.deleteRoleMenuRelByRoleId(role.getId());
            for(Menu menu:role.getMenus()){
                RoleMenuRel roleMenuRel = new RoleMenuRel(UUID.randomUUID().toString().replaceAll("-",""),role.getId(),menu.getId());
                int n2 = manageMapper.addRoleMenuRel(roleMenuRel);
                for(Menu secondMenu:menu.getMenus()){
                    RoleMenuRel roleMenuRel2 = new RoleMenuRel(UUID.randomUUID().toString().replaceAll("-",""),role.getId(),secondMenu.getId());
                    int n3 = manageMapper.addRoleMenuRel(roleMenuRel2);
                }
            }
        }
        return b;
    }

    @Override
    public boolean addRole(Role role) {
        role.setId(UUID.randomUUID().toString().replaceAll("-",""));
        role.setCreateDate(new Date());
        role.setState("1");
        boolean b = manageMapper.insertRole(role);
        if(role.getMenus()!=null && role.getMenus().size()>0){
            int n = manageMapper.deleteRoleMenuRelByRoleId(role.getId());
            for(Menu menu:role.getMenus()){
                RoleMenuRel roleMenuRel = new RoleMenuRel(UUID.randomUUID().toString().replaceAll("-",""),role.getId(),menu.getId());
                int n2 = manageMapper.addRoleMenuRel(roleMenuRel);
                for(Menu secondMenu:menu.getMenus()){
                    RoleMenuRel roleMenuRel2 = new RoleMenuRel(UUID.randomUUID().toString().replaceAll("-",""),role.getId(),secondMenu.getId());
                    int n3 = manageMapper.addRoleMenuRel(roleMenuRel2);
                }
            }
        }
        return b;
    }

    @Override
    public ManageUser getManageUserById(String id) {
        return manageMapper.selectManageUserById(id);
    }

    @Override
    public boolean updateManageUserProfile(ManageUser manageUser) {
        return manageMapper.updateManageUserProfile(manageUser) > 0;
    }

    @Override
    public boolean updatePassword(String id, String oldPassword, String newPassword) {
        ManageUser manageUser = manageMapper.selectManageUserByIdAndPassword(id, oldPassword);
        if (manageUser == null) {
            return false;
        }
        return manageMapper.updateManageUserPassword(id, newPassword) > 0;
    }
}
