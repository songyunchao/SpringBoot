package com.fsun.domain.model;

import java.util.Date;

public class SysMessage {
    /**
     * 
     * 表字段 : sys_message.id
     */
    private String id;

    /**
     * 
     * 表字段 : sys_message.task_no
     */
    private String taskNo;

    /**
     * 
     * 表字段 : sys_message.status
     */
    private Short status;

    /**
     * 
     * 表字段 : sys_message.file_name
     */
    private String fileName;

    /**
     * 
     * 表字段 : sys_message.url
     */
    private String url;

    /**
     * 
     * 表字段 : sys_message.descr
     */
    private String descr;
    
    /**
     * 表字段 : sys_message.username
     */
    private String username; 

    /**
     * 
     * 表字段 : sys_message.create_name
     */
    private String createName;

    /**
     * 
     * 表字段 : sys_message.create_time
     */
    private Date createTime;

    /**
     * 
     * 表字段 : sys_message.update_name
     */
    private String updateName;

    /**
     * 
     * 表字段 : sys_message.update_time
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo == null ? null : taskNo.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}