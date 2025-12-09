package com.kzl.dao;

import com.alibaba.fastjson.JSONArray;
import com.kzl.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManageMapper {

    List<Menu> selectFirstMenuByRoleId(String roleId,String parentId);

    Information selectInformationByRoleId(String roleId);

    JSONArray selectMenuList();

    boolean insertMenu(Menu menu);

    boolean updateMenu(Menu menu);

    boolean updateCollege(College college);

    boolean insertCollege(College college);

    JSONArray selectCollegeList();

    JSONArray selectInformationList();

    Information selectInformationById(String id);

    boolean updateInformation(Information information);

    boolean addInformation(Information information);

    boolean deleteInformation(String id);

    List<Teacher> selectTeacherList(Teacher teacher);

    boolean updateTeacher(Teacher teacher);

    List<Student> selectStudentList(Student student);

    boolean updateStudent(Student student);

    List<Menu> selectMenuByRoleId(String roleId);

    List<Menu> selectAllMenuList();

    List<Role> selectRoleList();

    boolean updateRole(Role role);

    int addRoleMenuRel(RoleMenuRel roleMenuRel);

    int deleteRoleMenuRelByRoleId(String roleId);

    boolean insertRole(Role role);
}
