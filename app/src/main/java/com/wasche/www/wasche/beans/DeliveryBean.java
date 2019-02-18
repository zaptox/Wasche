package com.wasche.www.wasche.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliveryBean implements Serializable
{

    @SerializedName("response")
    @Expose
    String response;

    @SerializedName("id")
    @Expose
    Integer id;

    @SerializedName("address")
    @Expose
    String address;

    @SerializedName("urgent")
    @Expose
    UrgentBean urgentBean;


    @SerializedName("customer_id")
    @Expose
    Integer customerId;


    @SerializedName("expected_date")
    @Expose
    String expectedDeliveryDate;


    @SerializedName("delivered_at")
    @Expose
    String deliveryDate;

    @SerializedName("quantity")
    @Expose
    int quantity;


    @SerializedName("urgent_status")
    @Expose
    Integer urgent_status;


    @SerializedName("delivery_status")
    @Expose
    String deliveryStatus;


    @SerializedName("total_delivery_cost")
    @Expose
    double totalDeliveryCost;

    @SerializedName("rider")
    @Expose
    RiderBean riderBean;


    @SerializedName("created_at")
    @Expose
    String createdAt;


    @SerializedName("updated_at")
    @Expose
    String updatedAt;

    @SerializedName("created_by")
    @Expose
    Integer createdBy;

    @SerializedName("updated_by")
    @Expose
    Integer updatedBy;

    @SerializedName("active")
    @Expose
    Integer active;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getUrgent_status() {
        return urgent_status;
    }

    public void setUrgent_status(Integer urgent_status) {
        this.urgent_status = urgent_status;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public double getTotalDeliveryCost() {
        return totalDeliveryCost;
    }

    public void setTotalDeliveryCost(double totalDeliveryCost) {
        this.totalDeliveryCost = totalDeliveryCost;
    }

    public RiderBean getRiderBean() {
        return riderBean;
    }

    public void setRiderBean(RiderBean riderBean) {
        this.riderBean = riderBean;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }


    public UrgentBean getUrgentBean() {
        return urgentBean;
    }

    public void setUrgentBean(UrgentBean urgentBean) {
        this.urgentBean = urgentBean;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DeliveryBean{" +
                "response='" + response + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", urgentBean=" + urgentBean +
                ", customerId=" + customerId +
                ", expectedDeliveryDate='" + expectedDeliveryDate + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", quantity=" + quantity +
                ", urgent_status=" + urgent_status +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", totalDeliveryCost='" + totalDeliveryCost + '\'' +
                ", riderBean=" + riderBean +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", active=" + active +
                '}';
    }
}

