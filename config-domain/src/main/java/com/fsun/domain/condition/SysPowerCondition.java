package com.fsun.domain.condition;

import java.util.Date;

import com.fsun.domain.common.BaseCondition;

public class SysPowerCondition extends BaseCondition{
	/**
     * ID
     * 表字段 : sys_power.id
     */
    private String id;

    /**
     * 名称
     * 表字段 : sys_power.name
     */
    private String name;

    /**
     * 编码
     * 表字段 : sys_power.code
     */
    private String code;

    /**
     * 所属模块
     * 表字段 : sys_power.menu_id
     */
    private String menuId;

    /**
     * 状态
     * 表字段 : sys_power.enabled
     */
    private Boolean enabled;

    /**
     * 
     * 表字段 : sys_power.url
     */
    private String url;

    /**
     * 排序
     * 表字段 : sys_power.priority
     */
    private Integer priority;

    /**
     * 创建时间
     * 表字段 : sys_power.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * 表字段 : sys_power.create_man_id
     */
    private String createManId;

    /**
     * 更新人
     * 表字段 : sys_power.update_man_id
     */
    private String updateManId;

    /**
     * 更新时间
     * 表字段 : sys_power.update_time
     */
    private Date updateTime;

    /**
     * 备注
     * 表字段 : sys_power.description
     */
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateManId() {
        return createManId;
    }

    public void setCreateManId(String createManId) {
        this.createManId = createManId == null ? null : createManId.trim();
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