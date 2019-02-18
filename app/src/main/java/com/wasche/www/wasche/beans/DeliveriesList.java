package com.wasche.www.wasche.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeliveriesList {

    @SerializedName("deliveries")
    private ArrayList<DeliveryBean> deliveriesList;

    public ArrayList<DeliveryBean> getDeliveriesList() {
        return deliveriesList;
    }

    public void setDeliveriesList(ArrayList<DeliveryBean> deliveriesList) {
        this.deliveriesList = deliveriesList;
    }
}
