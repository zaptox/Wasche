package com.wasche.www.wasche.restcalls;


import com.google.gson.JsonArray;
import com.wasche.www.wasche.beans.ServiceItemsList;
import com.wasche.www.wasche.beans.ServicesList;
import com.wasche.www.wasche.beans.UrgentBean;
import com.wasche.www.wasche.beans.UrgentCostList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI {


    @GET("services/GetAllServices.php")
    Call<ServicesList> getServicesData();


    @GET("services/GetServiceItems.php")
    Call<ServiceItemsList> getServicesItems();


    @GET("services/GetUrgent.php")
    Call<UrgentCostList> getUrgentDetails();


}
