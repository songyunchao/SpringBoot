package com.spring.domain;

public class SysLogWithBLOBs extends SysLog {
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
}