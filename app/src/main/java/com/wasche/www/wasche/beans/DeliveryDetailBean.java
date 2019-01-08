package com.wasche.www.wasche.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliveryDetailBean implements Serializable
{

    @SerializedName("response")
    @Expose
    String response;

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("delivery")
    @Expose
    DeliveryBean deliveryBean;

    @SerializedName("service")
    @Expose
    ServiceBean serviceBean;


    @SerializedName("item")
    @Expose
    ItemBean itemBean;


    @SerializedName("cost")
    @Expose
    String cost;




    @SerializedName("created_at")
    @Expose
    String createdAt;


    @SerializedName("updated_at")
    @Expose
    String updatedAt;


    @SerializedName("active")
    @Expose
    Integer active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DeliveryBean getDeliveryBean() {
        return deliveryBean;
    }

    public void setDeliveryBean(DeliveryBean deliveryBean) {
        this.deliveryBean = deliveryBean;
    }

    public ServiceBean getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(ServiceBean serviceBean) {
        this.serviceBean = serviceBean;
    }

    public ItemBean getItemBean() {
        return itemBean;
    }

    public void setItemBean(ItemBean itemBean) {
        this.itemBean = itemBean;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "DeliveryDetailBean{" +
                "id=" + id +
                ", deliveryBean=" + deliveryBean +
                ", serviceBean=" + serviceBean +
                ", itemBean=" + itemBean +
                ", cost='" + cost + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", active=" + active +
                '}';
    }
}

