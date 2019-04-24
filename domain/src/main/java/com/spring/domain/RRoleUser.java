package com.spring.domain;

public class RRoleUser {
    /**
     * ID
     * 表字段 : r_role_user.id
     */
    private String id;

    /**
     * 角色ID
     * 表字段 : r_role_user.role_id
     */
    private String roleId;

    /**
     * 用户ID
     * 表字段 : r_role_user.user_id
     */
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}