package com.wasche.www.wasche.dao;

import com.wasche.www.wasche.beans.ServiceBean;
import com.wasche.www.wasche.beans.UrgentBean;
import com.wasche.www.wasche.dbtables.ServiceTable;

import java.util.List;

public interface ServiceDao {

    public void addService(List<ServiceBean> services);
    public List<ServiceTable> getServices();
    public  void loadServices();

}
