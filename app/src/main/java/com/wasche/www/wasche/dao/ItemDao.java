package com.wasche.www.wasche.dao;

import com.wasche.www.wasche.beans.ItemBean;
import com.wasche.www.wasche.beans.UrgentBean;
import com.wasche.www.wasche.dbtables.ItemTable;

import java.util.List;

public interface ItemDao {


    public void addItems(List<ItemBean> items,double urgentPercent);
    public List<ItemTable> getItemsByServiceId(Integer id);
    public  void loadItems(double urgentDeliveryPercent);
}
