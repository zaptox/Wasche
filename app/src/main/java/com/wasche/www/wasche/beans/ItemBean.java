package com.wasche.www.wasche.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemBean implements Serializable
{

    @SerializedName("response")
    private String response;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("cost")
    private double cost;

    @SerializedName("service_id")
    private  Integer serviceId;


    @SerializedName("image")
    private  String  image;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "response='" + response + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", serviceId=" + serviceId +
                ", image='" + image + '\'' +
                '}';
    }
}

