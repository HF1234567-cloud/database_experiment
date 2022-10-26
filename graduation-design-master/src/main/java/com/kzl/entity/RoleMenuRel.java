package com.kzl.entity;

import lombok.Data;

//角色菜单关联表
@Data
public class RoleMenuRel {
    private String id;
    private String roleId;
    private String menuId;

    public RoleMenuRel(String id, String roleId, String menuId) {
        this.id = id;
        this.roleId = roleId;
        this.menuId = menuId;
    }
}
