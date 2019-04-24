package com.spring.domain;

public class RRolePower {
    /**
     * ID
     * 表字段 : r_role_power.id
     */
    private String id;

    /**
     * 角色ID
     * 表字段 : r_role_power.role_id
     */
    private String roleId;

    /**
     * 操作ID
     * 表字段 : r_role_power.power_id
     */
    private String powerId;

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

    public String getPowerId() {
        return powerId;
    }

    public void setPowerId(String powerId) {
        this.powerId = powerId == null ? null : powerId.trim();
    }
}