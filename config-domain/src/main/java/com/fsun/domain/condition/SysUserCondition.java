package com.fsun.domain.condition;

import java.util.Date;

import com.fsun.domain.common.BaseCondition;

public class SysUserCondition extends BaseCondition {
    /**
     * 用户ID
     * 表字段 : sys_user.id
     */
    private String id;

    /**
     * 用户编码
     * 表字段 : sys_user.code
     */
    private String code;

    /**
     * 用户帐号
     * 表字段 : sys_user.username
     */
    private String username;

    /**
     * 用户密码
     * 表字段 : sys_user.password
     */
    private String password;

    /**
     * 用户中文名称
     * 表字段 : sys_user.realname
     */
    private String realname;

    /**
     * 电子邮件
     * 表字段 : sys_user.email
     */
    private String email;

    /**
     * 手机号码
     * 表字段 : sys_user.telphone
     */
    private String telphone;

    /**
     * 是否是系统管理员
     * 表字段 : sys_user.issys
     */
    private Boolean issys;

    /**
     * 状态
     * 表字段 : sys_user.enabled
     */
    private Boolean enabled;

    /**
     * 排序
     * 表字段 : sys_user.priority
     */
    private Integer priority;

    /**
     * 创建人
     * 表字段 : sys_user.create_man_id
     */
    private String createManId;

    /**
     * 创建时间
     * 表字段 : sys_user.create_time
     */
    private Date createTime;

    /**
     * 最后更新人
     * 表字段 : sys_user.update_man_id
     */
    private String updateManId;

    /**
     * 最后更新时间
     * 表字段 : sys_user.update_time
     */
    private Date updateTime;

    /**
     * 
     * 表字段 : sys_user.shop_id
     */
    private String shopId;

    /**
     * 备注
     * 表字段 : sys_user.description
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public Boolean getIssys() {
        return issys;
    }

    public void setIssys(Boolean issys) {
        this.issys = issys;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}