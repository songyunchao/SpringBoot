package com.spring.domain;

public class RRoleMenu {
    /**
     * ID
     * 表字段 : r_role_menu.id
     */
    private String id;

    /**
     * 角色ID
     * 表字段 : r_role_menu.role_id
     */
    private String roleId;

    /**
     * 菜单ID
     * 表字段 : r_role_menu.menu_id
     */
    private String menuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}