package com.wasche.www.wasche.dbtables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;
import com.wasche.www.wasche.beans.ServiceBean;

import java.io.Serializable;
@Table(name="ItemTable")
public class ItemTable extends Model
{


    @Column(name="itemId" ,unique = true)
    private Integer itemId;

    @Column(name="name" ,notNull = false)
    private String name;

    @Column(name="cost" ,notNull = false)
    private  double cost;

    @Column(name="urgentCost" ,notNull = false)
    private  double urgentCost;


    @Column(name="urgentPercent" ,notNull = false)
    private  double urgentPercent;



    @Column(name="serviceId" ,notNull = false)
    private Integer serviceId;

    @Column(name="image" ,notNull = false)
    private  String  image;

    public double getUrgentCost() {
        return urgentCost;
    }

    public void setUrgentCost(double urgentCost) {
        this.urgentCost = urgentCost;
    }

    public double getUrgentPercent() {
        return urgentPercent;
    }

    public void setUrgentPercent(double urgentPercent) {
        this.urgentPercent = urgentPercent;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
}

