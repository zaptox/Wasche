package com.wasche.www.wasche.dbtables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Table(name="UrgentTable")
public class UrgentTable extends Model
{
    @Column(name="ItemId" ,unique = true)
    private Integer ItemId;

    @Column(name="urgentPercent" ,notNull = false)
    private String urgentPercent;

    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer itemId) {
        ItemId = itemId;
    }

    public String getUrgentPercent() {
        return urgentPercent;
    }

    public void setUrgentPercent(String urgentPercent) {
        this.urgentPercent = urgentPercent;
    }
}

