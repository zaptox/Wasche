package com.wasche.www.wasche.beans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeliveryDetailsList {

    @SerializedName("deliveryDetails")
    private ArrayList<DeliveryDetailBean> deliveryDetails;

    public ArrayList<DeliveryDetailBean> getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(ArrayList<DeliveryDetailBean> deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    @Override
    public String toString() {
        return "DeliveryDetailsList{" +
                "deliveryDetails=" + deliveryDetails +
                '}';
    }
}
