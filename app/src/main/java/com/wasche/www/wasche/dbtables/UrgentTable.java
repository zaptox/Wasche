package com.wasche.www.wasche.dbtables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Table(name="UrgentTable")
public class UrgentTable extends Model
{
    @Column(name="urgentId" ,unique = true)
    private Integer urgentId;

    @Column(name="urgentPercent" ,notNull = false)
    private double urgentPercent;

    public Integer getUrgentId() {
        return urgentId;
    }

    public void setUrgentId(Integer itemId) {
        urgentId = itemId;
    }

    public double getUrgentPercent() {
        return urgentPercent;
    }

    public void setUrgentPercent(double urgentPercent) {
        this.urgentPercent = urgentPercent;
    }
}

