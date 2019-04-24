package com.spring.domain;

import java.util.Date;

public class SysRole {
    /**
     * 角色ID
     * 表字段 : sys_role.id
     */
    private String id;

    /**
     * 角色编码
     * 表字段 : sys_role.code
     */
    private String code;

    /**
     * 角色名称
     * 表字段 : sys_role.name
     */
    private String name;

    /**
     * 状态
     * 表字段 : sys_role.enabled
     */
    private Boolean enabled;

    /**
     * 排序
     * 表字段 : sys_role.priority
     */
    private Integer priority;

    /**
     * 创建人
     * 表字段 : sys_role.create_man_id
     */
    private String createManId;

    /**
     * 创建时间
     * 表字段 : sys_role.create_time
     */
    private Date createTime;

    /**
     * 最后更新人
     * 表字段 : sys_role.update_man_id
     */
    private String updateManId;

    /**
     * 最后更新时间
     * 表字段 : sys_role.update_time
     */
    private Date updateTime;

    /**
     * 备注
     * 表字段 : sys_role.description
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCreateManId() {
        return createManId;
    }

    public void setCreateManId(String createManId) {
        this.createManId = createManId == null ? null : createManId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateManId() {
        return updateManId;
    }

    public void setUpdateManId(String updateManId) {
        this.updateManId = updateManId == null ? null : updateManId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}