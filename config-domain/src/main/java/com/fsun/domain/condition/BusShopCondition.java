package com.fsun.domain.condition;

import java.util.Date;

import com.fsun.domain.common.BaseCondition;

/**
 * 
 * @author fsun
 * @date 2020年5月7日
 */
public class BusShopCondition extends BaseCondition{
    /**
     * 店仓编号
     * 表字段 : bus_shop.shop_id
     */
    private String shopId;

    /**
     * 店铺编码
     * 表字段 : bus_shop.shop_code
     */
    private String shopCode;

    /**
     * 店仓名称
     * 表字段 : bus_shop.shop_name
     */
    private String shopName;

    /**
     * 位置
     * 表字段 : bus_shop.position
     */
    private String position;

    /**
     * 地址
     * 表字段 : bus_shop.address
     */
    private String address;

    /**
     * 手机号
     * 表字段 : bus_shop.tel
     */
    private String tel;

    /**
     * 联系人
     * 表字段 : bus_shop.contacts
     */
    private String contacts;

    /**
     * 备注
     * 表字段 : bus_shop.memo
     */
    private String memo;

    /**
     * 是否可用
     * 表字段 : bus_shop.enabled
     */
    private Boolean enabled;

    /**
     * 创建时间
     * 表字段 : bus_shop.created_time
     */
    private Date createdTime;

    /**
     * 更新时间
     * 表字段 : bus_shop.updated_time
     */
    private Date updatedTime;

    /**
     * 创建人id
     * 表字段 : bus_shop.created_id
     */
    private String createdId;

    /**
     * 更新人id
     * 表字段 : bus_shop.updated_id
     */
    private String updatedId;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedId() {
        return createdId;
    }

    public void setCreatedId(String createdId) {
        this.createdId = createdId == null ? null : createdId.trim();
    }

    public String getUpdatedId() {
        return updatedId;
    }

    public void setUpdatedId(String updatedId) {
        this.updatedId = updatedId == null ? null : updatedId.trim();
    }
}