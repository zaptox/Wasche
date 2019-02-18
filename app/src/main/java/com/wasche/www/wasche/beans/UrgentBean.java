package com.wasche.www.wasche.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class UrgentBean implements Serializable
{

    @SerializedName("response")
    private  String response;

    @SerializedName("id")
    private Integer id;

    @SerializedName("percent")
    private  double urgentPercent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public double getUrgentPercent() {
        return urgentPercent;
    }

    public void setUrgentPercent(double urgentPercent) {
        this.urgentPercent = urgentPercent;
    }

    @Override
    public String toString() {
        return "UrgentBean{" +
                "id=" + id +
                ", urgentPercent='" + urgentPercent + '\'' +
                '}';
    }
}

