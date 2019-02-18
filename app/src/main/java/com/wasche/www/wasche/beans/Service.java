package com.wasche.www.wasche.beans;

import com.google.gson.annotations.SerializedName;

public class Service {


    @SerializedName("name")
    private int  serviceId;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;


    public int getServiceId() {
        return serviceId;
    }


    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ServiceTable{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
