package com.wasche.www.wasche.daoImp;

import android.util.Log;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.wasche.www.wasche.beans.ServiceBean;
import com.wasche.www.wasche.beans.ServicesList;
import com.wasche.www.wasche.dao.ServiceDao;
import com.wasche.www.wasche.dbtables.ServiceTable;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.ServiceAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDaoImpl implements ServiceDao {

    @Override
    public void addService(List<ServiceBean> services) {
        if(new Select().all().from(ServiceTable.class).execute().size()!=0){
            new Delete().from(ServiceTable.class).execute();
        }
        for(ServiceBean serviceBean:services){
            ServiceTable service=new ServiceTable();
            service.setName(serviceBean.getName());
            service.setImage(serviceBean.getImageBaseUrl()+"/"+serviceBean.getImagePath());
            service.setServiceId(serviceBean.getServiceId());
            service.save();
        }
    }

    @Override
    public List<ServiceTable> getServices() {

        List<ServiceTable>services=new Select().all().from(ServiceTable.class).execute();
 return  services;
    }

    @Override
    public void loadServices() {
        ServiceAPI serviceAPI = ApiClient.getApiClient().create(ServiceAPI.class);
        Call<ServicesList> call=serviceAPI.getServicesData();
        call.enqueue(new Callback<ServicesList>() {
            @Override
            public void onResponse(Call<ServicesList> call, Response<ServicesList> response) {
                addService(response.body().getServicesArrayList());
                Log.d("Services",response.body().getServicesArrayList().toString());
            }

            @Override
            public void onFailure(Call<ServicesList> call, Throwable t) {

            }
        });
    }
}
