package com.wasche.www.wasche.dao;

import com.wasche.www.wasche.beans.UrgentBean;
import com.wasche.www.wasche.dbtables.UrgentTable;

public interface UrgentDao
{


    public void addUrgentPercent(UrgentBean urgentBean);
    public UrgentTable getUrgentPercent();
    public  double loadUrgentPercent();

}
