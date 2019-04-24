package com.spring.domain;

public class ServiceRegistryWithBLOBs extends ServiceRegistry {
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