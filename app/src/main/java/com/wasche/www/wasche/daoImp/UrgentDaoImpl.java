package com.wasche.www.wasche.daoImp;

import android.util.Log;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.wasche.www.wasche.beans.UrgentBean;
import com.wasche.www.wasche.beans.UrgentCostList;
import com.wasche.www.wasche.dao.UrgentDao;
import com.wasche.www.wasche.dbtables.UrgentTable;
import com.wasche.www.wasche.restcalls.ApiClient;
import com.wasche.www.wasche.restcalls.ServiceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrgentDaoImpl implements UrgentDao {
    public double urgentDeliveryPercent=0.0;
    @Override
    public void addUrgentPercent(UrgentBean urgentBean) {

        if(new Select().all().from(UrgentTable.class).execute().size()!=0){
            new Delete().from(UrgentTable.class).execute();
        }


        UrgentTable urgentTable = new UrgentTable();
        urgentTable.setUrgentPercent(urgentBean.getUrgentPercent());
        urgentTable.setUrgentId(urgentBean.getId());
        urgentTable.save();
    }

    @Override
    public UrgentTable getUrgentPercent() {
        return  new Select().all().from(UrgentTable.class).executeSingle();
    }

    @Override
    public double loadUrgentPercent() {

        ServiceAPI UApi = ApiClient.getApiClient().create(ServiceAPI.class);
        Call<UrgentCostList> callU=UApi.getUrgentDetails();
        callU.enqueue(new Callback<UrgentCostList>() {
            @Override
            public void onResponse(Call<UrgentCostList> call, Response<UrgentCostList> response) {
                urgentDeliveryPercent=Double.parseDouble(response.body().getUrgentBeanList().get(0).getUrgentPercent()+"");
                addUrgentPercent(response.body().getUrgentBeanList().get(0));
            }

            @Override
            public void onFailure(Call<UrgentCostList> call, Throwable t) {
                Log.d("Excep",t.getMessage());
            }
        });
    return  urgentDeliveryPercent;}
}
