package com.wasche.www.wasche.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceBeanList {

    @SerializedName("items")
    private ArrayList<Service> serviceList;

    public ArrayList<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
