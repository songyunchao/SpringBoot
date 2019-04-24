package com.spring.domain;

public class SysSettings {
    /**
     * 
     * 表字段 : sys_settings.id
     */
    private String id;

    /**
     * 
     * 表字段 : sys_settings.code
     */
    private String code;

    /**
     * 
     * 表字段 : sys_settings.name
     */
    private String name;

    /**
     * 
     * 表字段 : sys_settings.value
     */
    private String value;

    /**
     * 
     * 表字段 : sys_settings.group_name
     */
    private String groupName;

    /**
     * 
     * 表字段 : sys_settings.editor
     */
    private String editor;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }
}