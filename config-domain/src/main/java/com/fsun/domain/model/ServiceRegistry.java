package com.fsun.domain.model;

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
    
    /**
     * 
     * 表字段 : service_registry.description
     */
    private String description;

    /**
     * 
     * 表字段 : service_registry.img_path
     */
    private String imgPath;

    /**
     * 
     * 表字段 : service_registry.index_url
     */
    private String indexUrl;

    /**
     * 
     * 表字段 : service_registry.service_id
     */
    private String serviceId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl == null ? null : indexUrl.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }
}