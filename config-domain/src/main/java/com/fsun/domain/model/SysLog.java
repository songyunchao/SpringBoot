package com.fsun.domain.model;

import java.util.Date;

public class SysLog {
    /**
     * 
     * 表字段 : sys_log.id
     */
    private String id;

    /**
     * 操作用户
     * 表字段 : sys_log.user_id
     */
    private String userId;

    /**
     * 日志级别
     * 表字段 : sys_log.log_level
     */
    private Short logLevel;

    /**
     * 操作时间
     * 表字段 : sys_log.operate_time
     */
    private Date operateTime;

    /**
     * 操作类别(1登录、2退出、3插入、4删除、5更新、6打印、7上传、8其他')
     * 表字段 : sys_log.operate_type
     */
    private Short operateType;

    /**
     * 浏览器
     * 表字段 : sys_log.broswer
     */
    private String broswer;
    
    /**
     * 日志内容
     * 表字段 : sys_log.log_content
     */
    private String logContent;

    /**
     * 节点
     * 表字段 : sys_log.note
     */
    private String note;

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Short getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Short logLevel) {
        this.logLevel = logLevel;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Short getOperateType() {
        return operateType;
    }

    public void setOperateType(Short operateType) {
        this.operateType = operateType;
    }

    public String getBroswer() {
        return broswer;
    }

    public void setBroswer(String broswer) {
        this.broswer = broswer == null ? null : broswer.trim();
    }
}