package com.spring.domain;

public class ServiceRegistry {
    /**
     * 
     * 表字段 : service_registry.id
     */
    private Long id;

    /**
     * 
     * 表字段 : service_registry.evaluation_order
     */
    private Integer evaluationOrder;

    /**
     * 
     * 表字段 : service_registry.ignore_attributes
     */
    private Boolean ignoreAttributes;

    /**
     * 
     * 表字段 : service_registry.name
     */
    private String name;

    /**
     * 
     * 表字段 : service_registry.power_code
     */
    private String powerCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEvaluationOrder() {
        return evaluationOrder;
    }

    public void setEvaluationOrder(Integer evaluationOrder) {
        this.evaluationOrder = evaluationOrder;
    }

    public Boolean getIgnoreAttributes() {
        return ignoreAttributes;
    }

    public void setIgnoreAttributes(Boolean ignoreAttributes) {
        this.ignoreAttributes = ignoreAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPowerCode() {
        return powerCode;
    }

    public void setPowerCode(String powerCode) {
        this.powerCode = powerCode == null ? null : powerCode.trim();
    }
}