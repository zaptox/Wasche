package com.wasche.www.wasche.beans;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

public class ServiceBean
{
    @SerializedName("id")
    private int  serviceId;

    @SerializedName("name")
    private String name;

    @SerializedName("image_base_url")
    private String imageBaseUrl;

    @SerializedName("image_path")
    private String imagePath;

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public void setImageBaseUrl(String imageBaseUrl) {
        this.imageBaseUrl = imageBaseUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

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

    @Override
    public String toString() {
        return "ServiceBean{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", imageBaseUrl='" + imageBaseUrl + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

