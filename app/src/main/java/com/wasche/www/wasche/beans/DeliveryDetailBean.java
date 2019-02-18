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

    @SerializedName("qunatity")
    @Expose
    Integer qunatity;


    @SerializedName("service_id")
    @Expose
    int serviceId;


    @SerializedName("customer_id")
    @Expose
    int customerId;



    @SerializedName("item_id")
    @Expose
    int itemId;


    @SerializedName("cost")
    @Expose
    double cost;

    @SerializedName("total_cost")
    @Expose
    double totalCost;


    @SerializedName("item_image_url")
    @Expose
    String itemImageUrl;


    @SerializedName("service_name")
    @Expose
    String serviceName;


    @SerializedName("item_name")
    @Expose
    String itemName;

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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getItemImageUrl() {
        return itemImageUrl;
    }

    public void setItemImageUrl(String itemImageUrl) {
        this.itemImageUrl = itemImageUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQunatity() {
        return qunatity;
    }

    public void setQunatity(Integer qunatity) {
        this.qunatity = qunatity;
    }

    @Override
    public String toString() {
        return "DeliveryDetailBean{" +
                "response='" + response + '\'' +
                ", id=" + id +
                ", qunatity=" + qunatity +
                ", serviceId=" + serviceId +
                ", customerId=" + customerId +
                ", itemId=" + itemId +
                ", cost=" + cost +
                ", totalCost=" + totalCost +
                ", itemImageUrl='" + itemImageUrl + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}

