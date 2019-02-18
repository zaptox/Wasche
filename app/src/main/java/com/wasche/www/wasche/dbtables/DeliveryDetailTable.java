package com.wasche.www.wasche.dbtables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name="DeliveryDetailTable")
public class DeliveryDetailTable extends Model
{


    @Column(name="dDetailId" ,unique = true)
    private int dDetailId;

    @Column(name="userID" ,notNull = false)
    private  int userID =0;

    @Column(name="itemId" ,notNull = false)
    private int itemId;

    @Column(name="itemName" ,notNull = false)
    private  String  itemName;

    @Column(name="cost" ,notNull = false)
    private  double cost;

    @Column(name="totalCost" ,notNull = false)
    private  double totalCost;


    @Column(name="quantity" ,notNull = false)
    private int quantity;

    @Column(name="urgentCost" ,notNull = false)
    private  double urgentCost;

    @Column(name="totalUrgentCost" ,notNull = false)
    private  double totalUrgentCost;


    @Column(name="urgentPercent" ,notNull = false)
    private  double urgentPercent;

    @Column(name="urgentStatus" ,notNull = false)
    private  int urgentStatus;

    @Column(name="serviceId" ,notNull = false)
    private Integer serviceId;

    @Column(name="serviceName" ,notNull = false)
    private String serviceName;


    @Column(name="itemImage" ,notNull = false)
    private  String  itemImage;

    public int getdDetailId() {
        return dDetailId;
    }

    public void setdDetailId(int dDetailId) {
        this.dDetailId = dDetailId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUrgentCost() {
        return urgentCost;
    }

    public void setUrgentCost(double urgentCost) {
        this.urgentCost = urgentCost;
    }

    public double getTotalUrgentCost() {
        return totalUrgentCost;
    }

    public void setTotalUrgentCost(double totalUrgentCost) {
        this.totalUrgentCost = totalUrgentCost;
    }

    public double getUrgentPercent() {
        return urgentPercent;
    }

    public void setUrgentPercent(double urgentPercent) {
        this.urgentPercent = urgentPercent;
    }

    public int getUrgentStatus() {
        return urgentStatus;
    }

    public void setUrgentStatus(int urgentStatus) {
        this.urgentStatus = urgentStatus;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "DeliveryDetailTable{" +
                "dDetailId=" + dDetailId +
                ", userID=" + userID +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", cost=" + cost +
                ", totalCost=" + totalCost +
                ", quantity=" + quantity +
                ", urgentCost=" + urgentCost +
                ", totalUrgentCost=" + totalUrgentCost +
                ", urgentPercent=" + urgentPercent +
                ", urgentStatus=" + urgentStatus +
                ", serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                ", itemImage='" + itemImage + '\'' +
                '}';
    }
}

