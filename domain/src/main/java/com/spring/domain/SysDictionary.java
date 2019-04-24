package com.spring.domain;

public class SysDictionary {
    /**
     * 
     * 表字段 : sys_dictionary.id
     */
    private Integer id;

    /**
     * 
     * 表字段 : sys_dictionary.key
     */
    private String key;

    /**
     * 
     * 表字段 : sys_dictionary.value
     */
    private String value;

    /**
     * 
     * 表字段 : sys_dictionary.name
     */
    private String name;

    /**
     * 
     * 表字段 : sys_dictionary.pid
     */
    private Integer pid;

    /**
     * 
     * 表字段 : sys_dictionary.enabled
     */
    private Boolean enabled;

    /**
     * 
     * 表字段 : sys_dictionary.sequence
     */
    private Integer sequence;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}