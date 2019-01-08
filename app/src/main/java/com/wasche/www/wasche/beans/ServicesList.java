package com.wasche.www.wasche.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServicesList {

    @SerializedName("service")
    private ArrayList<ServiceBean> serviceList;

    public ArrayList<ServiceBean> getServicesArrayList() {
        return serviceList;
    }

    public void setNoticeArrayList(ArrayList<ServiceBean> serviceList) {
        this.serviceList = serviceList;
    }

}
