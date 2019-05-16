package com.fsun.domain.condition;

import java.util.Date;

public class SysMenuCondition {
	/**
     * ID
     * 表字段 : sys_menu.id
     */
    private String id;

    /**
     * 菜单编码
     * 表字段 : sys_menu.code
     */
    private String code;

    /**
     * 菜单名称
     * 表字段 : sys_menu.name
     */
    private String name;

    /**
     * 链接地址
     * 表字段 : sys_menu.url
     */
    private String url;

    /**
     * 节点图标
     * 表字段 : sys_menu.icon
     */
    private String icon;

    /**
     * 创建时间
     * 表字段 : sys_menu.create_time
     */
    private Date createTime;

    /**
     * 更新人ID
     * 表字段 : sys_menu.update_man_id
     */
    private String updateManId;

    /**
     * 最后更新时间
     * 表字段 : sys_menu.update_time
     */
    private Date updateTime;

    /**
     * 是否有叶子
     * 表字段 : sys_menu.isleaf
     */
    private Boolean isleaf;

    /**
     * 节点图标CSS类名
     * 表字段 : sys_menu.iconcls
     */
    private String iconcls;

    /**
     * 是否扩展（1-代表扩展，0代表收缩）
     * 表字段 : sys_menu.expanded
     */
    private Boolean expanded;

    /**
     * 状态
     * 表字段 : sys_menu.enabled
     */
    private Boolean enabled;

    /**
     * 排序
     * 表字段 : sys_menu.priority
     */
    private Integer priority;

    /**
     * 创建人ID
     * 表字段 : sys_menu.create_man_id
     */
    private String createManId;

    /**
     * 父节点
     * 表字段 : sys_menu.pid
     */
    private String pid;

    /**
     * 备注
     * 表字段 : sys_menu.description
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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

    public Boolean getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Boolean isleaf) {
        this.isleaf = isleaf;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls == null ? null : iconcls.trim();
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}