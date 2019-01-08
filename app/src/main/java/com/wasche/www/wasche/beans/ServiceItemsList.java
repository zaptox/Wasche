package com.wasche.www.wasche.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceItemsList {

    @SerializedName("items")
    private ArrayList<ItemBean> serviceItemsList;

    public ArrayList<ItemBean> getServiceItemsList() {
        return serviceItemsList;
    }

    public void setServiceItemsList(ArrayList<ItemBean> serviceItemsList) {
        this.serviceItemsList = serviceItemsList;
    }

    @Override
    public String toString() {
        return "ServiceItemsList{" +
                "serviceItemsList=" + serviceItemsList +
                '}';
    }
}
